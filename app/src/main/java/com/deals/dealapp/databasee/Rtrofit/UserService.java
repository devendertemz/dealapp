package com.deals.dealapp.databasee.Rtrofit;

import com.deals.dealapp.ModelResponse.AddtoCart_Resp;
import com.deals.dealapp.ModelResponse.AddtoWishlist_Resp;
import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.ModelResponse.Get_trending_categories_Response;
import com.deals.dealapp.ModelResponse.Getproducts_bybrandResp;
import com.deals.dealapp.ModelResponse.HomepageSlider;
import com.deals.dealapp.ModelResponse.ProductDeatilsResponse;
import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.model.JewalleryModel;
import com.deals.dealapp.model.JewalleryModelResponse;
import com.deals.dealapp.model.UpgradeHomeModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    //getcategories
    @GET("getcategories")
    Call<List<CategoryListModel>> getcategories();

    //getsubcategory
    @GET("getsubcategory/{cat_id}")
    Call<List<Secondcategory_itemlist>> getSubCategories(
            @Path("cat_id") String cat_id
    );

    @GET("getsliders")
    Call<List<HomepageSlider>> getHomepageslider();

    //getProductDetails
    @GET("getproductbyid/{subcategoryid}")
    Call<List<ProductDeatilsResponse>> getProductDetails(
            @Path("subcategoryid") String subcategory_id
    );

    @GET("get_home_single_trending_categories")
    Call<List<JewalleryModelResponse>> getjewellery();


    @GET("get_home_single_trending_categories")
    Call<ResponseBody> getjewelleryy();


    @GET("get_upgrade_your_home_categories")
    Call<List<UpgradeHomeModel>> UpgradeHome();


    @GET("get_trending_categories")
    Call<List<Get_trending_categories_Response>> Get_trending_categories();
/*
     @FormUrlEncoded
    @POST("getproducts_bybrand")
    Call<List<Getproducts_bybrandResp>> getproducts_bybrand(
            @Field("sub_categories_id") String id
    );*/

    @FormUrlEncoded
    @POST("getproducts_bybrand")
    Call<ResponseBody> getproducts_bybrand(
            @Field("sub_categories_id") String id
    );

    @FormUrlEncoded
    @POST("product/add_to_cart")
    Call<AddtoCart_Resp> add_to_cart(
            @Field("product_id") String product_id,
            @Field("quantity") String quantity,
            @Field("user_id") String user_id

    );
    @FormUrlEncoded
    @POST("product/add_to_wishlist")
    Call<AddtoWishlist_Resp> AddtoWishlist_Resp(
            @Field("product_id") String product_id,
            @Field("user_id") String user_id

    );




   // Call<CelebrityResp> getCelebrity(@Query("page") int page, @Query("limit") int limit, @Body EventFilterBody filter);
//
  /*  @FormUrlEncoded
    @POST("details_registration.php/")
    Call<ResponseBody> send_school_deatils(

            @Field("id") String id,
            @Field("school name") String school_name,
            @Field("school categories") String school_categories
    );*/

}
