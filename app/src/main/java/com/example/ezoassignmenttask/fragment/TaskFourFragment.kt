package com.example.ezoassignmenttask.fragment

import android.media.MediaPlayer
import android.media.ToneGenerator
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.adapters.TaskFourRecyclerAdapter
import com.example.ezoassignmenttask.databinding.FragmentTaskFourBinding
import com.example.ezoassignmenttask.models.FourMetaData

class TaskFourFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentTaskFourBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: TaskFourRecyclerAdapter
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskFourBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer()
        initRecyclerView()
        binding.apply {
            ivBack.setOnClickListener(this@TaskFourFragment)
//            val video = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.test_video)
//            val videoUrl = Uri.parse("https://d2s7v2mzcfdtyp.cloudfront.net/event/f457c545a9ded88f18ecee47145a72c0/card/Pzs44FQZpLEIwVtjpey2Aycte7rHjf1A2wWQUpj7.mp4")
//            vvVideo.setVideoURI(videoUrl)
//            vvVideo.start()
        }
    }

    private fun initRecyclerView() {
        mAdapter = TaskFourRecyclerAdapter()
        binding.rvTestImage.adapter = mAdapter
        val dataList = ArrayList<FourMetaData>()

        val path101 = "android.resource://" + requireActivity().packageName + "/" + R.raw.tone101
        val path102 = "android.resource://" + requireActivity().packageName + "/" + R.raw.tone102
        val path103 = "android.resource://" + requireActivity().packageName + "/" + R.raw.tone103
        val path104 = "android.resource://" + requireActivity().packageName + "/" + R.raw.tone104
        val path105 = "android.resource://" + requireActivity().packageName + "/" + R.raw.tone105


//        val tone0 = FourMetaData(id = 0, itemPrice = 0, itemBarcode =  ToneGenerator.MAX_VOLUME, viewType = 0)
        val tone1 = FourMetaData(id = 1, itemPrice = 1, itemBarcode =  ToneGenerator.MIN_VOLUME, viewType = 0)
        val tone2 = FourMetaData(id = 2, itemPrice = 2, itemBarcode =  ToneGenerator.TONE_CDMA_ABBR_ALERT, viewType = 0)
        val tone3 = FourMetaData(id = 3, itemPrice = 3, itemBarcode =  ToneGenerator.TONE_CDMA_ABBR_INTERCEPT, viewType = 0)
        val tone4 = FourMetaData(id = 4, itemPrice = 4, itemBarcode =  ToneGenerator.TONE_CDMA_ABBR_REORDER, viewType = 0)
        val tone5 = FourMetaData(id = 5, itemPrice = 5, itemBarcode =  ToneGenerator.TONE_CDMA_ALERT_AUTOREDIAL_LITE, viewType = 0)
        val tone6 = FourMetaData(id = 6, itemPrice = 6, itemBarcode =  ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, viewType = 0)
        val tone7 = FourMetaData(id = 7, itemName = "Star", itemPrice = 7, itemBarcode =  ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE, viewType = 0)//
        val tone8 = FourMetaData(id = 8, itemPrice = 8, itemBarcode =  ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE, viewType = 0)
        val tone9 = FourMetaData(id = 9, itemPrice = 9, itemBarcode =  ToneGenerator.TONE_CDMA_ANSWER, viewType = 0)
        val tone10 = FourMetaData(id = 10, itemName = "Star", itemPrice = 10, itemBarcode =  ToneGenerator.TONE_CDMA_CALLDROP_LITE, viewType = 0)//
        val tone11 = FourMetaData(id = 11, itemPrice = 11, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP, viewType = 0)
//        val tone12 = FourMetaData(id = 12, itemPrice = 12, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL, viewType = 0)
//        val tone13 = FourMetaData(id = 13, itemPrice = 13, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT3, viewType = 0)
//        val tone14 = FourMetaData(id = 14, itemPrice = 14, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT5, viewType = 0)
//        val tone15 = FourMetaData(id = 15, itemPrice = 15, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT6, viewType = 0)
//        val tone16 = FourMetaData(id = 16, itemPrice = 16, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT7, viewType = 0)
//        val tone17 = FourMetaData(id = 17, itemPrice = 17, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING, viewType = 0)
//        val tone18 = FourMetaData(id = 18, itemPrice = 18, itemBarcode =  ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI, viewType = 0)
        val tone19 = FourMetaData(id = 19, itemPrice = 19, itemBarcode =  ToneGenerator.TONE_CDMA_CONFIRM, viewType = 0)
        val tone20 = FourMetaData(id = 20, itemPrice = 20, itemBarcode =  ToneGenerator.TONE_CDMA_DIAL_TONE_LITE, viewType = 0)
        val tone21 = FourMetaData(id = 21, itemPrice = 21, itemBarcode =  ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK, viewType = 0)
        val tone22 = FourMetaData(id = 22, itemPrice = 22, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_L, viewType = 0)
        val tone23 = FourMetaData(id = 23, itemPrice = 23, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_PBX_L, viewType = 0)
        val tone24 = FourMetaData(id = 24, itemPrice = 24, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_PBX_SLS, viewType = 0)
        val tone25 = FourMetaData(id = 25, itemPrice = 25, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_PBX_SS, viewType = 0)
        val tone26 = FourMetaData(id = 26, itemPrice = 26, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_PBX_SSL, viewType = 0)
        val tone27 = FourMetaData(id = 27, itemPrice = 27, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_PBX_S_X4, viewType = 0)
        val tone28 = FourMetaData(id = 28, itemPrice = 28, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_SLS, viewType = 0)
        val tone29 = FourMetaData(id = 29, itemPrice = 29, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_SS, viewType = 0)
        val tone30 = FourMetaData(id = 30, itemPrice = 30, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_SSL, viewType = 0)
        val tone31 = FourMetaData(id = 31, itemPrice = 31, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_SS_2, viewType = 0)
        val tone32 = FourMetaData(id = 32, itemPrice = 32, itemBarcode =  ToneGenerator.TONE_CDMA_HIGH_S_X4, viewType = 0)
        val tone33 = FourMetaData(id = 33, itemPrice = 33, itemBarcode =  ToneGenerator.TONE_CDMA_INTERCEPT, viewType = 0)
        val tone34 = FourMetaData(id = 34, itemPrice = 34, itemBarcode =  ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, viewType = 0)
        val tone35 = FourMetaData(id = 35, itemPrice = 35, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_L, viewType = 0)
        val tone36 = FourMetaData(id = 36, itemPrice = 36, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_PBX_L, viewType = 0)
        val tone37 = FourMetaData(id = 37, itemPrice = 37, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_PBX_SLS, viewType = 0)
        val tone38 = FourMetaData(id = 38, itemPrice = 38, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_PBX_SS, viewType = 0)
        val tone39 = FourMetaData(id = 39, itemPrice = 39, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_PBX_SSL, viewType = 0)
        val tone40 = FourMetaData(id = 40, itemPrice = 40, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_PBX_S_X4, viewType = 0)
        val tone41 = FourMetaData(id = 41, itemPrice = 41, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_SLS, viewType = 0)
        val tone42 = FourMetaData(id = 42, itemPrice = 42, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_SS, viewType = 0)
        val tone43 = FourMetaData(id = 43, itemPrice = 43, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_SSL, viewType = 0)
        val tone44 = FourMetaData(id = 44, itemPrice = 44, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_SS_2, viewType = 0)
        val tone45 = FourMetaData(id = 45, itemPrice = 45, itemBarcode =  ToneGenerator.TONE_CDMA_LOW_S_X4, viewType = 0)
        val tone46 = FourMetaData(id = 46, itemPrice = 46, itemBarcode =  ToneGenerator.TONE_CDMA_MED_L, viewType = 0)
        val tone47 = FourMetaData(id = 47, itemPrice = 47, itemBarcode =  ToneGenerator.TONE_CDMA_MED_PBX_L, viewType = 0)
        val tone48 = FourMetaData(id = 48, itemPrice = 48, itemBarcode =  ToneGenerator.TONE_CDMA_MED_PBX_SLS, viewType = 0)
        val tone49 = FourMetaData(id = 49, itemPrice = 49, itemBarcode =  ToneGenerator.TONE_CDMA_MED_PBX_SS, viewType = 0)
        val tone50 = FourMetaData(id = 50, itemPrice = 50, itemBarcode =  ToneGenerator.TONE_CDMA_MED_PBX_SSL, viewType = 0)
        val tone51 = FourMetaData(id = 51, itemPrice = 51, itemBarcode =  ToneGenerator.TONE_CDMA_MED_PBX_S_X4, viewType = 0)
        val tone52 = FourMetaData(id = 52, itemPrice = 52, itemBarcode =  ToneGenerator.TONE_CDMA_MED_SLS, viewType = 0)
        val tone53 = FourMetaData(id = 53, itemPrice = 53, itemBarcode =  ToneGenerator.TONE_CDMA_MED_SS, viewType = 0)
        val tone54 = FourMetaData(id = 54, itemPrice = 54, itemBarcode =  ToneGenerator.TONE_CDMA_MED_SSL, viewType = 0)
        val tone55 = FourMetaData(id = 55, itemPrice = 55, itemBarcode =  ToneGenerator.TONE_CDMA_MED_SS_2, viewType = 0)
        val tone56 = FourMetaData(id = 56, itemPrice = 56, itemBarcode =  ToneGenerator.TONE_CDMA_MED_S_X4, viewType = 0)
        val tone57 = FourMetaData(id = 57, itemPrice = 57, itemBarcode =  ToneGenerator.TONE_CDMA_NETWORK_BUSY, viewType = 0)
        val tone58 = FourMetaData(id = 58, itemPrice = 58, itemBarcode =  ToneGenerator.TONE_CDMA_NETWORK_BUSY_ONE_SHOT, viewType = 0)
        val tone59 = FourMetaData(id = 59, itemPrice = 59, itemBarcode =  ToneGenerator.TONE_CDMA_NETWORK_CALLWAITING, viewType = 0)
        val tone60 = FourMetaData(id = 60, itemPrice = 60, itemBarcode =  ToneGenerator.TONE_CDMA_NETWORK_USA_RINGBACK, viewType = 0)
        val tone61 = FourMetaData(id = 61, itemPrice = 61, itemBarcode =  ToneGenerator.TONE_CDMA_ONE_MIN_BEEP, viewType = 0)
        val tone62 = FourMetaData(id = 62, itemPrice = 62, itemBarcode =  ToneGenerator.TONE_CDMA_PIP, viewType = 0)
        val tone63 = FourMetaData(id = 63, itemPrice = 63, itemBarcode =  ToneGenerator.TONE_CDMA_PRESSHOLDKEY_LITE, viewType = 0)
        val tone64 = FourMetaData(id = 64, itemPrice = 64, itemBarcode =  ToneGenerator.TONE_CDMA_REORDER, viewType = 0)
        val tone65 = FourMetaData(id = 65, itemPrice = 65, itemBarcode =  ToneGenerator.TONE_CDMA_SIGNAL_OFF, viewType = 0)
        val tone66 = FourMetaData(id = 66, itemName = "Star", itemPrice = 66, itemBarcode =  ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE, viewType = 0)//
        val tone67 = FourMetaData(id = 67, itemPrice = 67, itemBarcode =  ToneGenerator.TONE_DTMF_0, viewType = 0)
        val tone68 = FourMetaData(id = 68, itemPrice = 68, itemBarcode =  ToneGenerator.TONE_DTMF_1, viewType = 0)
        val tone69 = FourMetaData(id = 69, itemPrice = 69, itemBarcode =  ToneGenerator.TONE_DTMF_2, viewType = 0)
        val tone70 = FourMetaData(id = 70, itemPrice = 70, itemBarcode =  ToneGenerator.TONE_DTMF_3, viewType = 0)
        val tone71 = FourMetaData(id = 71, itemPrice = 71, itemBarcode =  ToneGenerator.TONE_DTMF_4, viewType = 0)
        val tone72 = FourMetaData(id = 72, itemPrice = 72, itemBarcode =  ToneGenerator.TONE_DTMF_5, viewType = 0)
        val tone73 = FourMetaData(id = 73, itemPrice = 73, itemBarcode =  ToneGenerator.TONE_DTMF_6, viewType = 0)
        val tone74 = FourMetaData(id = 74, itemPrice = 74, itemBarcode =  ToneGenerator.TONE_DTMF_7, viewType = 0)
        val tone75 = FourMetaData(id = 75, itemPrice = 75, itemBarcode =  ToneGenerator.TONE_DTMF_8, viewType = 0)
        val tone76 = FourMetaData(id = 76, itemPrice = 76, itemBarcode =  ToneGenerator.TONE_DTMF_9, viewType = 0)
        val tone77 = FourMetaData(id = 77, itemPrice = 77, itemBarcode =  ToneGenerator.TONE_DTMF_A, viewType = 0)
        val tone78 = FourMetaData(id = 78, itemPrice = 78, itemBarcode =  ToneGenerator.TONE_DTMF_B, viewType = 0)
        val tone79 = FourMetaData(id = 79, itemPrice = 79, itemBarcode =  ToneGenerator.TONE_DTMF_C, viewType = 0)
        val tone80 = FourMetaData(id = 80, itemPrice = 80, itemBarcode =  ToneGenerator.TONE_DTMF_D, viewType = 0)
        val tone81 = FourMetaData(id = 81, itemPrice = 81, itemBarcode =  ToneGenerator.TONE_DTMF_P, viewType = 0)
        val tone82 = FourMetaData(id = 82, itemPrice = 82, itemBarcode =  ToneGenerator.TONE_DTMF_S, viewType = 0)
        val tone83 = FourMetaData(id = 83, itemName = "Star", itemPrice = 83, itemBarcode =  ToneGenerator.TONE_PROP_ACK, viewType = 0)//
        val tone84 = FourMetaData(id = 84, itemName = "Star", itemPrice = 84, itemBarcode =  ToneGenerator.TONE_PROP_BEEP, viewType = 0)//
        val tone85 = FourMetaData(id = 85, itemName = "Star", itemPrice = 85, itemBarcode =  ToneGenerator.TONE_PROP_BEEP2, viewType = 0)//
        val tone86 = FourMetaData(id = 86, itemName = "Star", itemPrice = 86, itemBarcode =  ToneGenerator.TONE_PROP_NACK, viewType = 0)//
        val tone87 = FourMetaData(id = 87, itemPrice = 87, itemBarcode =  ToneGenerator.TONE_PROP_PROMPT, viewType = 0)
        val tone88 = FourMetaData(id = 88, itemPrice = 88, itemBarcode =  ToneGenerator.TONE_SUP_BUSY, viewType = 0)
        val tone89 = FourMetaData(id = 89, itemPrice = 89, itemBarcode =  ToneGenerator.TONE_SUP_CALL_WAITING, viewType = 0)
        val tone90 = FourMetaData(id = 90, itemName = "Star", itemPrice = 90, itemBarcode =  ToneGenerator.TONE_SUP_CONFIRM, viewType = 0)//
        val tone91 = FourMetaData(id = 91, itemPrice = 91, itemBarcode =  ToneGenerator.TONE_SUP_CONGESTION, viewType = 0)
        val tone92 = FourMetaData(id = 92, itemPrice = 92, itemBarcode =  ToneGenerator.TONE_SUP_CONGESTION_ABBREV, viewType = 0)
        val tone93 = FourMetaData(id = 93, itemPrice = 93, itemBarcode =  ToneGenerator.TONE_SUP_DIAL, viewType = 0)
        val tone94 = FourMetaData(id = 94, itemPrice = 94, itemBarcode =  ToneGenerator.TONE_SUP_ERROR, viewType = 0)
        val tone95 = FourMetaData(id = 95, itemPrice = 95, itemBarcode =  ToneGenerator.TONE_SUP_INTERCEPT, viewType = 0)
        val tone96 = FourMetaData(id = 96, itemPrice = 96, itemBarcode =  ToneGenerator.TONE_SUP_INTERCEPT_ABBREV, viewType = 0)
        val tone97 = FourMetaData(id = 97, itemPrice = 97, itemBarcode =  ToneGenerator.TONE_SUP_PIP, viewType = 0)
        val tone98 = FourMetaData(id = 98, itemPrice = 98, itemBarcode =  ToneGenerator.TONE_SUP_RADIO_ACK, viewType = 0)
        val tone99 = FourMetaData(id = 99, itemPrice = 99, itemBarcode =  ToneGenerator.TONE_SUP_RADIO_NOTAVAIL, viewType = 0)
        val tone100 = FourMetaData(id = 100, itemPrice = 100, itemBarcode =  ToneGenerator.TONE_SUP_RINGTONE, viewType = 0)
        val tone101 = FourMetaData(id = 101, itemPrice = 101, itemBarcode = R.raw.tone101, viewType = 0)
        val tone102 = FourMetaData(id = 102, itemPrice = 102, itemBarcode = R.raw.tone102, viewType = 0)
        val tone103 = FourMetaData(id = 103, itemPrice = 103, itemBarcode = R.raw.tone103, viewType = 0)
        val tone104 = FourMetaData(id = 104, itemPrice = 104, itemBarcode = R.raw.tone104, viewType = 0)
        val tone105 = FourMetaData(id = 105, itemPrice = 105, itemBarcode = R.raw.tone105, viewType = 0)


//        dataList.add(tone0)
        dataList.add(tone1)
        dataList.add(tone2)
        dataList.add(tone3)
        dataList.add(tone4)
        dataList.add(tone5)
        dataList.add(tone6)
        dataList.add(tone7)
        dataList.add(tone8)
        dataList.add(tone9)
        dataList.add(tone10)
        dataList.add(tone11)
//        dataList.add(tone12)
//        dataList.add(tone13)
//        dataList.add(tone14)
//        dataList.add(tone15)
//        dataList.add(tone16)
//        dataList.add(tone17)
//        dataList.add(tone18)
        dataList.add(tone19)
        dataList.add(tone20)
        dataList.add(tone21)
        dataList.add(tone22)
//        dataList.add(tone23)
//        dataList.add(tone24)
//        dataList.add(tone25)
//        dataList.add(tone26)
//        dataList.add(tone27)
//        dataList.add(tone28)
//        dataList.add(tone29)
//        dataList.add(tone30)
//        dataList.add(tone31)
//        dataList.add(tone32)
        dataList.add(tone33)
        dataList.add(tone34)
        dataList.add(tone35)
//        dataList.add(tone36)
//        dataList.add(tone37)
//        dataList.add(tone38)
//        dataList.add(tone39)
//        dataList.add(tone40)
//        dataList.add(tone41)
//        dataList.add(tone42)
//        dataList.add(tone43)
//        dataList.add(tone44)
//        dataList.add(tone45)
        dataList.add(tone46)
//        dataList.add(tone47)
//        dataList.add(tone48)
//        dataList.add(tone49)
//        dataList.add(tone50)
//        dataList.add(tone51)
//        dataList.add(tone52)
//        dataList.add(tone53)
//        dataList.add(tone54)
//        dataList.add(tone55)
//        dataList.add(tone56)
        dataList.add(tone57)
        dataList.add(tone58)
//        dataList.add(tone59)
        dataList.add(tone60)
        dataList.add(tone61)
        dataList.add(tone62)
        dataList.add(tone63)
        dataList.add(tone64)
//        dataList.add(tone65)
        dataList.add(tone66)
//        dataList.add(tone67)
//        dataList.add(tone68)
//        dataList.add(tone69)
//        dataList.add(tone70)
//        dataList.add(tone71)
//        dataList.add(tone72)
//        dataList.add(tone73)
//        dataList.add(tone74)
//        dataList.add(tone75)
//        dataList.add(tone76)
//        dataList.add(tone77)
//        dataList.add(tone78)
//        dataList.add(tone79)
//        dataList.add(tone80)
//        dataList.add(tone81)
//        dataList.add(tone82)
        dataList.add(tone83)
        dataList.add(tone84)
        dataList.add(tone85)
        dataList.add(tone86)
        dataList.add(tone87)
//        dataList.add(tone88)
        dataList.add(tone89)
        dataList.add(tone90)
        dataList.add(tone91)
//        dataList.add(tone92)
        dataList.add(tone93)
        dataList.add(tone94)
        dataList.add(tone95)
//        dataList.add(tone96)
        dataList.add(tone97)
        dataList.add(tone98)
//        dataList.add(tone99)
        dataList.add(tone100)
        dataList.add(tone101)
        dataList.add(tone102)
        dataList.add(tone103)
        dataList.add(tone104)
        dataList.add(tone105)

        mAdapter.submitList(dataList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v?.id) {
                ivBack.id -> {
                    findNavController().navigate(R.id.action_taskFourFragment_to_mainFragment)
                }
            }
        }
    }

}











