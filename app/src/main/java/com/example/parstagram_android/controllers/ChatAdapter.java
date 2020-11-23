package com.example.parstagram_android.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram_android.R;
import com.example.parstagram_android.models.Message;
import com.example.parstagram_android.models.User;
import com.parse.ParseUser;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private static final int MESSAGE_OUTGOING = 123;
    private static final int MESSAGE_INCOMING = 321;
    private List<Message> mMessages;
    private Context mContext;
    private String userName;

    public ChatAdapter(Context context, String userName, List<Message> messages) {
        mMessages = messages;
        this.userName = userName;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isMe(position)) {
            return MESSAGE_OUTGOING;
        } else {
            return MESSAGE_INCOMING;
        }
    }

    private boolean isMe(int position) {
        if (userName == String.valueOf(ParseUser.getCurrentUser()))
            return true;
        else
            return false;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == MESSAGE_INCOMING) {
            View contactView = inflater.inflate(R.layout.item_message_incoming, parent, false);
            return new IncomingMessageViewHolder(contactView);

        } else if (viewType == MESSAGE_OUTGOING) {
            View contactView = inflater.inflate(R.layout.item_message_outgoing, parent, false);
            return new OutgoingMessageViewHolder(contactView);

        } else {
            throw new IllegalArgumentException("Unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = mMessages.get(position);
        holder.bindMessage(message);
    }


    public abstract class MessageViewHolder extends RecyclerView.ViewHolder {
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bindMessage(Message message);
    }

    public class IncomingMessageViewHolder extends MessageViewHolder {
        User userIn = new User();
        ImageView imageOther;
        TextView body;
        TextView name;

        public IncomingMessageViewHolder(View itemView) {
            super(itemView);
            imageOther = (ImageView)itemView.findViewById(R.id.ivProfileOther);
            body = (TextView)itemView.findViewById(R.id.tvBody);
            name = (TextView)itemView.findViewById(R.id.tvName);
        }

        @Override
        public void bindMessage(Message message) {
            Glide.with(mContext)
                    .load(userIn.getImage())
                    .circleCrop() // create an effect of a round profile picture
                    .into(imageOther);
            body.setText(message.getBody());
            name.setText(message.getUserId());
        }
    }

    public class OutgoingMessageViewHolder extends MessageViewHolder {
        User userOut = new User();
        ImageView imageMe;
        TextView body;

        public OutgoingMessageViewHolder(View itemView) {
            super(itemView);
            imageMe = (ImageView)itemView.findViewById(R.id.ivProfileMe);
            body = (TextView)itemView.findViewById(R.id.tvBody);
        }

        @Override
        public void bindMessage(Message message) {
            Glide.with(mContext)
                    .load(userOut.getImage())
                    .circleCrop() // create an effect of a round profile picture
                    .into(imageMe);
            body.setText(message.getBody());
        }
    }
}