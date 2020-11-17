package com.app.g_deck.network

import com.app.g_deck.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST("team_login.php")
  suspend  fun teamlogin(
        @Field("userid") userid:String,
        @Field("password") password:String
    ):Response<TeamLoginResponse>

    @FormUrlEncoded
    @POST("manager_login.php")
    suspend  fun siteManagerlogin(
        @Field("userid") userid:String,
        @Field("password") password:String
    ):Response<TeamLoginResponse>


    @FormUrlEncoded
    @POST("request_list.php")
    suspend  fun siteManagerRequestList(
        @Field("id") userid:String
    ):Response<Site_Manager_List>

    @FormUrlEncoded
    @POST("team_request_list.php")
    suspend  fun teamRequestList(
        @Field("team_id") userid:String
    ):Response<Site_Manager_List>

    @GET("get_bookingid.php")
    suspend  fun getBookingId(

    ):Response<BookingID>

    @FormUrlEncoded
    @POST("job_detail.php")
    suspend  fun getJobDetail(
    @Field("booking_id")booked_by: String
    ):Response<Site_Manager_List>

    @FormUrlEncoded
    @POST("work_request_detail.php")
    suspend  fun getWorkDetail(
        @Field("booking_id")booked_by: String
    ):Response<Site_Manager_List>

    @FormUrlEncoded
    @POST("add_request.php")
    suspend  fun addRequest(
        @Field("booking_id") booking_id:String,
        @Field("manager_id") manager_id:String,
        @Field("date") date:String,
        @Field("booked_by") booked_by:String,
        @Field("site_name") site_name:String,
        @Field("plot") plot:String,
        @Field("erect") elect:String,
        @Field("dismantle") dismantle:String,
        @Field("gf") gf:String,
        @Field("sf") sf:String,
        @Field("ff") ff:String,
        @Field("props") props:String,
        @Field("special_instruction") inst:String
    ):Response<AddRequest>

    @Multipart
    @POST("job_complete.php")
    suspend  fun completeJob(
        @Part("booking_id") booking_id:RequestBody,
        @Part("client_name") client_name:RequestBody,
        @Part("location") location:RequestBody,
        @Part("description") description:RequestBody,
        @Part("comments") comments:RequestBody,
        @Part("authorised_client_name") authorised_client_name:RequestBody,
        @Part("authorised_date") authorised_date:RequestBody,
        @Part("gdeck_representative_name") gdeck_representative_name:RequestBody,
        @Part("gdeck_representative_date") gdeck_representative_date:RequestBody,
        @Part("no_of_decks") no_of_decks:RequestBody,
        @Part("no_of_lifts") no_of_lifts:RequestBody,
        @Part("handrails") handrails:RequestBody,
        @Part("no_of_legs") no_of_legs:RequestBody,
        @Part("gates") gates:RequestBody,
        @Part("make_up_panels") make_up_panels:RequestBody,
        @Part("braces") braces:RequestBody,
        @Part("ladder_backets") ladder_backets:RequestBody,
        @Part("limitation_comments") limitation_comments:RequestBody,
        @Part("completed_plot") completed_plot:RequestBody,
        @Part("incompleted_plot") incompleted_plot:RequestBody,
        @Part foreman_sign:MultipartBody.Part,
        @Part gdeck_sign:MultipartBody.Part):Response<CompleteJob>


    @Multipart
    @POST("job_complete.php")
    suspend  fun completeJob1(
        @Part("booking_id") booking_id:RequestBody,
        @Part("client_name") client_name:RequestBody,
        @Part("location") location:RequestBody,
        @Part("description") description:RequestBody,
        @Part("comments") comments:RequestBody,
        @Part("authorised_client_name") authorised_client_name:RequestBody,
        @Part("authorised_date") authorised_date:RequestBody,
        @Part("gdeck_representative_name") gdeck_representative_name:RequestBody,
        @Part("gdeck_representative_date") gdeck_representative_date:RequestBody,
        @Part("no_of_decks") no_of_decks:RequestBody,
        @Part("no_of_lifts") no_of_lifts:RequestBody,
        @Part("handrails") handrails:RequestBody,
        @Part("no_of_legs") no_of_legs:RequestBody,
        @Part("gates") gates:RequestBody,
        @Part("make_up_panels") make_up_panels:RequestBody,
        @Part("braces") braces:RequestBody,
        @Part("ladder_backets") ladder_backets:RequestBody,
        @Part("limitation_comments") limitation_comments:RequestBody,
        @Part("completed_plot") completed_plot:RequestBody,
        @Part("incompleted_plot") incompleted_plot:RequestBody,
        @Part foreman_sign:MultipartBody.Part,
        @Part gdeck_sign:MultipartBody.Part,
        @Part image1:MultipartBody.Part):Response<CompleteJob>

    @Multipart
    @POST("job_complete.php")
    suspend  fun completeJob2(
        @Part("booking_id") booking_id:RequestBody,
        @Part("client_name") client_name:RequestBody,
        @Part("location") location:RequestBody,
        @Part("description") description:RequestBody,
        @Part("comments") comments:RequestBody,
        @Part("authorised_client_name") authorised_client_name:RequestBody,
        @Part("authorised_date") authorised_date:RequestBody,
        @Part("gdeck_representative_name") gdeck_representative_name:RequestBody,
        @Part("gdeck_representative_date") gdeck_representative_date:RequestBody,
        @Part("no_of_decks") no_of_decks:RequestBody,
        @Part("no_of_lifts") no_of_lifts:RequestBody,
        @Part("handrails") handrails:RequestBody,
        @Part("no_of_legs") no_of_legs:RequestBody,
        @Part("gates") gates:RequestBody,
        @Part("make_up_panels") make_up_panels:RequestBody,
        @Part("braces") braces:RequestBody,
        @Part("ladder_backets") ladder_backets:RequestBody,
        @Part("limitation_comments") limitation_comments:RequestBody,
        @Part("completed_plot") completed_plot:RequestBody,
        @Part("incompleted_plot") incompleted_plot:RequestBody,
        @Part foreman_sign:MultipartBody.Part,
        @Part gdeck_sign:MultipartBody.Part,
        @Part image1:MultipartBody.Part,
        @Part image2:MultipartBody.Part):Response<CompleteJob>

    @Multipart
    @POST("job_complete.php")
    suspend  fun completeJob3(
        @Part("booking_id") booking_id:RequestBody,
        @Part("client_name") client_name:RequestBody,
        @Part("location") location:RequestBody,
        @Part("description") description:RequestBody,
        @Part("comments") comments:RequestBody,
        @Part("authorised_client_name") authorised_client_name:RequestBody,
        @Part("authorised_date") authorised_date:RequestBody,
        @Part("gdeck_representative_name") gdeck_representative_name:RequestBody,
        @Part("gdeck_representative_date") gdeck_representative_date:RequestBody,
        @Part("no_of_decks") no_of_decks:RequestBody,
        @Part("no_of_lifts") no_of_lifts:RequestBody,
        @Part("handrails") handrails:RequestBody,
        @Part("no_of_legs") no_of_legs:RequestBody,
        @Part("gates") gates:RequestBody,
        @Part("make_up_panels") make_up_panels:RequestBody,
        @Part("braces") braces:RequestBody,
        @Part("ladder_backets") ladder_backets:RequestBody,
        @Part("limitation_comments") limitation_comments:RequestBody,
        @Part("completed_plot") completed_plot:RequestBody,
        @Part("incompleted_plot") incompleted_plot:RequestBody,
        @Part foreman_sign:MultipartBody.Part,
        @Part gdeck_sign:MultipartBody.Part,
        @Part image1:MultipartBody.Part,
        @Part image2:MultipartBody.Part,
        @Part image3:MultipartBody.Part
    ):Response<CompleteJob>
    companion object{
        private const val BASE_URL="http://sh013.hostgator.tempwebhost.net/~b2kteqnd/g-deck-admin/api/"
        operator fun invoke(

        ):AuthApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi::class.java)
        }
    }

}