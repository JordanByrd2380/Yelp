package edu.stanford.jbyrd23.yelpclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_restaraunt.view.*

class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaraunt, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount() = restaurants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant) {
            itemView.tvName.text = restaurant.name

            itemView.tvNumReviews.text ="${restaurant.numReviews} Reviews"
            itemView.tvAddress.text = restaurant.location.address
            itemView.tvCategory.text = restaurant.categories[0].title
            itemView.tvDistance.text = restaurant.displayDistance()
            itemView.tvPrice.text = restaurant.price
            if(restaurant.rating == 0.0){
                itemView.imageView2.setImageResource(R.drawable.stars_small_0)
            }
            if(restaurant.rating == 1.0){
                itemView.imageView2.setImageResource(R.drawable.stars_small_1)
            }
            if(restaurant.rating == 1.5){
                itemView.imageView2.setImageResource(R.drawable.stars_small_1_half)
            }
            if(restaurant.rating == 2.0){
                itemView.imageView2.setImageResource(R.drawable.stars_small_2)
            }
            if(restaurant.rating == 2.5){
                itemView.imageView2.setImageResource(R.drawable.stars_small_2_half)
            }
            if(restaurant.rating == 3.0){
                itemView.imageView2.setImageResource(R.drawable.stars_small_3)
            }
            if(restaurant.rating == 3.5){
                itemView.imageView2.setImageResource(R.drawable.stars_small_3_half)
            }
            if(restaurant.rating == 4.0){
                itemView.imageView2.setImageResource(R.drawable.stars_small_4)
            }
            if(restaurant.rating == 4.5){
                itemView.imageView2.setImageResource(R.drawable.stars_small_4_half)
            }
            if(restaurant.rating == 5.0){
                itemView.imageView2.setImageResource(R.drawable.stars_small_5)
            }

            Glide.with(context).load(restaurant.image_url).apply(RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(itemView.imageView)

        }
    }
}










