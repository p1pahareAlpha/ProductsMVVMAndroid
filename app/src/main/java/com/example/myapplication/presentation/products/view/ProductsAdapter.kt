package com.example.myapplication.presentation.products.view


import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.res.stringResource
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.common.AppConstants
import com.example.myapplication.di.network.load
import com.example.myapplication.domain.products.model.Product


class ProductsAdapter(private val mList: List<Product>)
    : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = mList[position]
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
        holder.productTitle.text = product.title
        holder.productThumbnail.load(product.thumbnail, requestOptions )

        var brandCategory : StringBuilder = java.lang.StringBuilder(product.brand)
            .append(AppConstants.EMPTY_SPACE).append(product.category)
        var price : StringBuilder = java.lang.StringBuilder(AppConstants.RUPEE_SIGN)
           .append(product.price)
        holder.productPrice.text =  price.toString()
        holder.productBrand.text = brandCategory.toString()
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, product )
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    interface OnClickListener {
        fun onClick(position: Int, product: Product)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val productThumbnail: ImageView = itemView.findViewById(R.id.productThumbnail)
        val productTitle: TextView = itemView.findViewById(R.id.productTitle)
        val productPrice : TextView = itemView.findViewById(R.id.productPrice)
        val productBrand : TextView = itemView.findViewById(R.id.productBrand)
    }
}
