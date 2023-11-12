package com.example.mad_practical_10_21012011081

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.ContactsContract
import android.provider.Telephony

class SMSBroadcastReceiver : BroadcastReceiver() {

    interface Listner {
        fun onTextReceived(sPhoneNo: String?, sMsg: String?)
    }
    private var listner: Listner? = null
    fun setListner(lis: Listner) {
        listner = lis
    }
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            var sPhoneNo = ""
            var sSMSBody = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    sPhoneNo = smsMessage.displayOriginatingAddress
                    sSMSBody += smsMessage.messageBody
                }
                if (listner != null) {
                    listner?.onTextReceived(sPhoneNo, sSMSBody)
                }
            }
        }

    }
}