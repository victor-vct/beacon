package com.vctapps.beacon.service.notifications

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import timber.log.Timber


class MyFirebaseInstanceIDService: FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        Timber.d("Firebase token: " + FirebaseInstanceId.getInstance().token)
    }
}