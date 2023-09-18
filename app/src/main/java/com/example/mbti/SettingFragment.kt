package com.example.mbti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.example.mbti.databinding.FragmentSettingBinding

class SettingFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        var logoutPref:Preference? = findPreference("logout")

//        logoutPref?.setOnPreferenceClickListener {
//            MyApplication.auth.signOut()
//        }


    }
}

