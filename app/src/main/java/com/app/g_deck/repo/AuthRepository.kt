package com.app.g_deck.repo

import com.app.g_deck.model.*
import com.app.g_deck.network.AuthApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part

class AuthRepository(
    private val authApi: AuthApi
):BaseRepository() {
   suspend fun teamLogin(
        userid:String,
        password:String
    ):TeamLoginResponse {
        return apiRequest {
            authApi.teamlogin(userid, password)
        }
    }


    suspend fun siteManagerLogin(
        userid:String,
        password:String
    ):TeamLoginResponse {
        return apiRequest {
            authApi.siteManagerlogin(userid, password)
        }
    }

    suspend fun siteManagerRequestList(
        userid:String
    ):Site_Manager_List {
        return apiRequest {
            authApi.siteManagerRequestList(userid)
        }
    }

    suspend fun teamRequestList(
        userid:String
    ):Site_Manager_List {
        return apiRequest {
            authApi.teamRequestList(userid)
        }
    }
   suspend fun getBookingID(

    ):BookingID {
        return apiRequest {
            authApi.getBookingId()
        }
    }

    suspend fun addRequest(
        booking_id:String,
        manager_id:String,
        date:String,
         booked_by:String,
         site_name:String,
        plot:String,
         elect:String,
         dismantle:String,
         gf:String,
         sf:String,
         ff:String,
         props:String,
        inst:String
    ):AddRequest {
        return apiRequest {
            authApi.addRequest(booking_id,manager_id, date, booked_by, site_name, plot, elect, dismantle, gf, sf, ff, props, inst)
        }
    }

    suspend fun getJobDetails(
        booking_id: String
    ):Site_Manager_List {
        return apiRequest {
            authApi.getJobDetail(booking_id)
        }
    }

    suspend fun getworkDetails(
        booking_id: String
    ):Site_Manager_List {
        return apiRequest {
            authApi.getWorkDetail(booking_id)
        }
    }

    suspend  fun completeJob(
        booking_id:RequestBody,
        client_name:RequestBody,
        location:RequestBody,
        description:RequestBody,
        comments:RequestBody,
        authorised_client_name:RequestBody,
        authorised_date:RequestBody,
        gdeck_representative_name:RequestBody,
        gdeck_representative_date:RequestBody,
        no_of_decks:RequestBody,
        no_of_lifts:RequestBody,
        handrails:RequestBody,
        no_of_legs:RequestBody,
        gates:RequestBody,
        make_up_panels:RequestBody,
        braces:RequestBody,
        ladder_backets:RequestBody,
        limitation_comments:RequestBody,
        complete:RequestBody,
        incomplete:RequestBody,

        foreman_sign:MultipartBody.Part,
        gdeck_sign:MultipartBody.Part): CompleteJob {
        return apiRequest {
            authApi.completeJob(booking_id, client_name, location, description, comments, authorised_client_name, authorised_date, gdeck_representative_name, gdeck_representative_date, no_of_decks, no_of_lifts, handrails, no_of_legs, gates, make_up_panels, braces, ladder_backets, limitation_comments,complete,incomplete ,foreman_sign, gdeck_sign)
        }
    }


    suspend  fun completeJob1(
         booking_id:RequestBody,
         client_name:RequestBody,
         location:RequestBody,
         description:RequestBody,
         comments:RequestBody,
        authorised_client_name:RequestBody,
         authorised_date:RequestBody,
         gdeck_representative_name:RequestBody,
         gdeck_representative_date:RequestBody,
         no_of_decks:RequestBody,
         no_of_lifts:RequestBody,
         handrails:RequestBody,
         no_of_legs:RequestBody,
        gates:RequestBody,
         make_up_panels:RequestBody,
         braces:RequestBody,
         ladder_backets:RequestBody,
         limitation_comments:RequestBody,
         complete:RequestBody,
         incomplete:RequestBody,
         foreman_sign:MultipartBody.Part,
         gdeck_sign:MultipartBody.Part,
         image1:MultipartBody.Part
    ):CompleteJob {
        return apiRequest {
            authApi.completeJob1(booking_id, client_name, location, description, comments, authorised_client_name, authorised_date, gdeck_representative_name, gdeck_representative_date, no_of_decks, no_of_lifts, handrails, no_of_legs, gates, make_up_panels, braces, ladder_backets, limitation_comments,complete,incomplete , foreman_sign, gdeck_sign,image1)
        }
    }


    suspend  fun completeJob2(
        booking_id:RequestBody,
        client_name:RequestBody,
        location:RequestBody,
        description:RequestBody,
        comments:RequestBody,
        authorised_client_name:RequestBody,
        authorised_date:RequestBody,
        gdeck_representative_name:RequestBody,
        gdeck_representative_date:RequestBody,
        no_of_decks:RequestBody,
        no_of_lifts:RequestBody,
        handrails:RequestBody,
        no_of_legs:RequestBody,
        gates:RequestBody,
        make_up_panels:RequestBody,
        braces:RequestBody,
        ladder_backets:RequestBody,
        limitation_comments:RequestBody,
        complete:RequestBody,
        incomplete:RequestBody,
        foreman_sign:MultipartBody.Part,
        gdeck_sign:MultipartBody.Part,
        image1:MultipartBody.Part,
        image2:MultipartBody.Part):CompleteJob {
        return apiRequest {
            authApi.completeJob2(booking_id, client_name, location, description, comments, authorised_client_name, authorised_date, gdeck_representative_name, gdeck_representative_date, no_of_decks, no_of_lifts, handrails, no_of_legs, gates, make_up_panels, braces, ladder_backets, limitation_comments,complete,incomplete , foreman_sign, gdeck_sign,image1, image2)
        }
    }

    suspend  fun completeJob3(
        booking_id:RequestBody,
        client_name:RequestBody,
        location:RequestBody,
        description:RequestBody,
        comments:RequestBody,
        authorised_client_name:RequestBody,
        authorised_date:RequestBody,
        gdeck_representative_name:RequestBody,
        gdeck_representative_date:RequestBody,
        no_of_decks:RequestBody,
        no_of_lifts:RequestBody,
        handrails:RequestBody,
        no_of_legs:RequestBody,
        gates:RequestBody,
        make_up_panels:RequestBody,
        braces:RequestBody,
        ladder_backets:RequestBody,
        limitation_comments:RequestBody,
        complete:RequestBody,
        incomplete:RequestBody,
        foreman_sign:MultipartBody.Part,
        gdeck_sign:MultipartBody.Part,
        image1:MultipartBody.Part,
        image2:MultipartBody.Part,
        image3:MultipartBody.Part
    ):CompleteJob {
        return apiRequest {
            authApi.completeJob3(booking_id, client_name, location, description, comments, authorised_client_name, authorised_date, gdeck_representative_name, gdeck_representative_date, no_of_decks, no_of_lifts, handrails, no_of_legs, gates, make_up_panels, braces, ladder_backets, limitation_comments,complete,incomplete , foreman_sign, gdeck_sign,image1, image2, image3)
        }
    }
}