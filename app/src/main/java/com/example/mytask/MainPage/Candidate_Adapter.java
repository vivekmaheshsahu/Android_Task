package com.example.mytask.MainPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytask.R;
import com.example.mytask.data.Candidate_details;

import java.util.List;

import static android.view.View.GONE;
import static com.example.mytask.R.drawable.default_image;

public class Candidate_Adapter extends RecyclerView.Adapter<Candidate_Adapter.ViewHolder> {

    private MainActivityInteractor MainActivityInteractor;
    private Context mContext;
    private List<Candidate_details> candidate_details;
    private Candidate_Adapter.OnItemClickListener mOnItemClickListener;
    private Bitmap defaultImage;

    public Candidate_Adapter(Context mContext, List<Candidate_details> womenList, Candidate_Adapter.OnItemClickListener mOnItemClickListener) {
        MainActivityInteractor = new MainActivityInteractor();
        this.mContext = mContext;
        this.candidate_details = womenList;
        this.mOnItemClickListener = mOnItemClickListener;
        defaultImage = BitmapFactory.decodeResource(mContext.getResources(), default_image);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anc_list_view, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.bindData(candidate_details.get(i), i);
    }


    @Override
    public int getItemCount() {
        return candidate_details.size();
    }

    public void swapDataList(List<Candidate_details> womenList) {
        this.candidate_details = womenList;
    }

    public interface OnItemClickListener {
        void onItemClick(String uniqueId, ImageView profileImage);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDetails, textViewcontact, textviewAddress, incompleteVisitlabel;
        ImageView accept, decline, accept_clicked, decline_clicked;
        ConstraintLayout constraintLayout;
        ImageView imageView;
        CardView cardView;
        TextView textView_ac, textView_de;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewDetails = itemView.findViewById(R.id.textview_details);
            textViewcontact = itemView.findViewById(R.id.textview_contact);
            textviewAddress = itemView.findViewById(R.id.textview_address);
            accept = itemView.findViewById(R.id.accept_button);
            decline = itemView.findViewById(R.id.decline_button);
            accept_clicked = itemView.findViewById(R.id.accept_button_clicked);
            decline_clicked = itemView.findViewById(R.id.decline_button_onclick);


            imageView = itemView.findViewById(R.id.imageview_photo);
            constraintLayout = itemView.findViewById(R.id.constraint_layout_root);
            //    incompleteVisitlabel = itemView.findViewById(R.id.womanStatusLabel);
            cardView = itemView.findViewById(R.id.card_view);
        }

        private void bindData(final Candidate_details listModel, final int position) {
            Log.d("RecyclerView", "vind data called " + position);
            if (listModel != null) {
                textViewName.setText(listModel.getTitle() + ". " + listModel.getFirst_name() + " " + listModel.getLast_name());
                String temp = listModel.getDob();
                String dob = temp.substring(0, 10);
                textViewDetails.setText("DOB : " + dob + " Age : " + listModel.getAge());
                textViewcontact.setText("Contact : " + listModel.getPhone() + " / " + listModel.getCell());
                textviewAddress.setText(listModel.getCity() + ", " + listModel.getCountry() + " Email : " + listModel.getEmail());


                byte[] blob = listModel.getImage();
                if (blob != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setImageBitmap(bitmap);
                } else imageView.setImageBitmap(defaultImage);

                String status = listModel.getStatus();
                Log.d("RecyclerView", "" + status);
                if (status.equals("NA")) {
                    accept.setVisibility(View.VISIBLE);
                    decline.setVisibility(View.VISIBLE);
                    accept_clicked.setVisibility(GONE);
                    decline_clicked.setVisibility(GONE);
                } else {
                    if (status.equals("Accept")) {
                        accept_clicked.setVisibility(View.VISIBLE);
                        accept.setVisibility(GONE);
                        decline_clicked.setVisibility(GONE);
                        decline.setVisibility(View.VISIBLE);
                    } else if (status.equals("decline")) {
                        decline.setVisibility(GONE);
                        accept.setVisibility(View.VISIBLE);
                        decline_clicked.setVisibility(View.VISIBLE);
                        accept_clicked.setVisibility(GONE);
                    }
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    ViewCompat.setTransitionName(imageView, listModel.getFirst_name());

            }

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(mContext, "Accept", Toast.LENGTH_SHORT).show();
                    int val = Integer.parseInt(listModel.getId());
                    long value = MainActivityInteractor.UpdateStatus(val, "Accept");

                    if (value != 0) {
                        notifyDataSetChanged();
                    }


                }
            });

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Decline", Toast.LENGTH_SHORT).show();
                    int val1 = Integer.parseInt(listModel.getId());
                    MainActivityInteractor.UpdateStatus(val1, "decline");
                    notifyItemChanged(position);
                }
            });

        }


    }
}
