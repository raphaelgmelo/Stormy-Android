package com.raphaelgmelo.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raphaelgmelo.stormy.R;
import com.raphaelgmelo.stormy.weather.Hour;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by raphaelgmelo on 18/04/15.
 */

// This one is using RecyclerView
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[] mHours;
    private Context mContext;

    public HourAdapter(Context context, Hour[] hours){
        mContext = context; // where this adaptor is being used
        mHours = hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        // this method creates the item if it doesn't exist

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hourly_list_item, viewGroup, false);

        HourViewHolder viewHolder = new HourViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder hourViewHolder, int i) {

        // this method binds data to an existing item

        hourViewHolder.bindHour(mHours[i]);

    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @InjectView(R.id.timeLabel) TextView mTimeLabel;
        @InjectView(R.id.summaryLabel) TextView mSummaryLabel;
        @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
        @InjectView(R.id.iconImageView) ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);

            // now using ButterKnife
            ButterKnife.inject(this,itemView);

            itemView.setOnClickListener(this); // "this" because this class implements OnClickListener

        }


        public void bindHour(Hour hour){

            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());

        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temperature = mTemperatureLabel.getText().toString();
            String summary = mSummaryLabel.getText().toString();

            String message = String.format("At %s it will be %s and %s",
                    time,
                    temperature,
                    summary);

            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }

}
