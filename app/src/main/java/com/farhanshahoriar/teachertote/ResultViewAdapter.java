package com.farhanshahoriar.resultcalculator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultViewAdapter extends RecyclerView.Adapter<ResultViewAdapter.ResultViewHolder> {

    public IndividualResult[] resultData = null;
    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder resultViewHolder, int pos) {
        String sMarks = resultData[pos].marks[0]+" \t"+resultData[pos].marks[1]+" \t"+resultData[pos].marks[2]+" \t"+ resultData[pos].totalMarks +"    \t"+ resultData[pos].newRoll;
        resultViewHolder.tvOldRoll.setText(String.valueOf(resultData[pos].oldRoll));
        resultViewHolder.tvName.setText(resultData[pos].nickName);
        resultViewHolder.tvMarks.setText(sMarks);
    }

    @Override
    public int getItemCount() {
        if(resultData == null)return 0;
        else return resultData.length;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        TextView tvOldRoll;
        TextView tvName;
        TextView tvMarks;

        public ResultViewHolder(View view){
            super(view);
            tvOldRoll = (TextView) view.findViewById(R.id.tv_item_text_id);
            tvName = (TextView) view.findViewById(R.id.tv_item_text_name);
            tvMarks = (TextView) view.findViewById(R.id.tv_item_text_marks);
        }

    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.result_item, viewGroup,false);
        return new ResultViewHolder(view);
    }

    public void setData(IndividualResult[] data){
        resultData = data;
        notifyDataSetChanged();
    }
}

