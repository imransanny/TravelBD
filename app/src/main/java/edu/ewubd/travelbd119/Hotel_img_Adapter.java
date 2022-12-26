
package edu.ewubd.travelbd119;

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.RecyclerView;

        import com.squareup.picasso.Picasso;

        import java.util.List;

public class Hotel_img_Adapter extends RecyclerView.Adapter<Hotel_img_Adapter.MyViewHolder> {


    private Context context;
    private List<Hotels_Upload> uploadList_hotels;

    public Hotel_img_Adapter(Context context, List<Hotels_Upload> uploadList_hotels) {
        this.context = context;
       this.uploadList_hotels = uploadList_hotels;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);;
        View view = layoutInflater.inflate(R.layout.hotel_image_adapter,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //data set korbo
        Hotels_Upload upload = uploadList_hotels.get(i);
        myViewHolder.textView.setText(upload.getImageName());
        myViewHolder.image_des.setText(upload.getImage_des());


        Picasso.get()
                .load(upload.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(myViewHolder.imageView);

        myViewHolder.star.setText(upload.getImage_Star());
        myViewHolder.location.setText(upload.getImage_Location());
        myViewHolder.price.setText(upload.getImage_Price());




        myViewHolder.layitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent (view.getContext(), Hotel_Details_Information.class);
                intent.putExtra("IMAGE_NAME",upload.getImageName());
                intent.putExtra("IMAGE_DESCRIPTION",upload.getImage_des());
                intent.putExtra("PLACE_IMAGE",upload.getImageUrl());

                intent.putExtra("PLACE_STAR",upload.getImage_Star());
                intent.putExtra("PLACE_LOCATION",upload.getImage_Location());
                intent.putExtra("PLACE_PRICE",upload.getImage_Price());

                view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return uploadList_hotels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView, image_des,star,location,price;
        ImageView imageView;
        LinearLayout layitem ;

        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            textView =   itemView.findViewById(R.id.cardText_id);
            star = itemView.findViewById(R.id.hote_star_id);
            location = itemView.findViewById(R.id.location_id_hotel);
            price = itemView.findViewById(R.id.price_hotel_id);
            imageView = itemView.findViewById(R.id.card_image_id);
            image_des = itemView.findViewById(R.id.hotel_description);
            layitem = itemView.findViewById(R.id.lay_item);

        }
    }



}
