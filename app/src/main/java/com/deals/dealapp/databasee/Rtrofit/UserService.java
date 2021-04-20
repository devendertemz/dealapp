package com.deals.dealapp.databasee.Rtrofit;

import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.ModelResponse.HomepageSlider;
import com.deals.dealapp.ModelResponse.ProductDeatilsResponse;
import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.model.JewalleryModel;
import com.deals.dealapp.model.UpgradeHomeModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

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

    @GET("getcategories")
    Call<List<JewalleryModel>> getjewellery();


    @GET("getcategories")
    Call<List<UpgradeHomeModel>> UpgradeHome();
}
