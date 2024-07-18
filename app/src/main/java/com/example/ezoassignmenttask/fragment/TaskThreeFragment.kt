package com.example.ezoassignmenttask.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.decode.SvgDecoder
import coil.load
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.FragmentTaskThreeBinding
import com.example.ezoassignmenttask.databinding.ItemDialogBinding


class TaskThreeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentTaskThreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTaskThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url0 =
            "https://hayhom.com/assets/icons/package-services-v2.png"//"https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/aa.svg"
        val url1 =
            "https://hayhom.com/assets/icons/all-services-v2.png"//"https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/adobe.svg"
        val url2 =
            "https://hayhom.com/assets/icons/return-invite-v2.png"//"https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/alphachannel.svg"
        val url3 =
            "https://hayhom.com/assets/icons/response-v2.png"//"https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/android.svg"
        val url4 =
            "https://hayhom.com/assets/icons/statistics-v2.png"//"https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/atom.svg"
//        val url5 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/bozo.svg"
//        val url5 = "https://svgshare.com/i/1354.svg"
        val url5 =
            "https://hayhom.com/assets/icons/scan-v2.png"//"https://i.ibb.co/FzPN58P/bill-new-3x.png"
        val urlPngText = "https://hayhom.com/assets/icons/send-v2.png"
        binding.apply {
            setImageViewGLide(ivGlideVector0, ivGlideVectorTint0, url0, "f6aa20")
            setImageViewGLide(ivGlideVector1, ivGlideVectorTint1, url1, "D8532A")
            setImageViewGLide(ivGlideVector2, ivGlideVectorTint2, url2, "AE2A4F")
            setImageViewGLide(ivGlideVector3, ivGlideVectorTint3, url3, "066877")
            setImageViewGLide(ivGlideVector4, ivGlideVectorTint4, url4, "a00505")
//            setImageView(ivGlideVector4, ivGlideVectorTint4, url4),
            setImageViewGLide(ivGlideVector5, ivGlideVectorTint5, url5, "f8a825")
            val htmlText =
                "<span style=\"color: #000;\">إحصائيات</span> لمعرفة القبول والاعتذار <span style=\"color: #000;\">بالاسم</span> أو <span style=\"color: #000;\">الرقم</span>."
            tvHtmlText.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            ivBack.setOnClickListener(this@TaskThreeFragment)
            ivGlideVector0.setOnClickListener(this@TaskThreeFragment)
            ivGlideVector1.setOnClickListener(this@TaskThreeFragment)
            ivGlideVector2.setOnClickListener(this@TaskThreeFragment)
            ivGlideVector3.setOnClickListener(this@TaskThreeFragment)
            ivGlideVector4.setOnClickListener(this@TaskThreeFragment)
            ivGlideVector5.setOnClickListener(this@TaskThreeFragment)
            ivGlideVectorTint0.setOnClickListener(this@TaskThreeFragment)
            ivGlideVectorTint1.setOnClickListener(this@TaskThreeFragment)
            ivGlideVectorTint2.setOnClickListener(this@TaskThreeFragment)
            ivGlideVectorTint3.setOnClickListener(this@TaskThreeFragment)
            ivGlideVectorTint4.setOnClickListener(this@TaskThreeFragment)
            ivGlideVectorTint5.setOnClickListener(this@TaskThreeFragment)

            Glide.with(requireActivity())
                .load(urlPngText)
                .placeholder(R.drawable.tf) // optional
                .error(R.drawable.tf_07) // optional
                .into(binding.ivPngTest)

            /*// Read Lottie JSON from raw resource
            val lottieJson = readRawResource(R.raw.arrow_animation)

            // Change layer colors
            val updatedLottieJsonOne =
                updateLayerColors(lottieJson, listOf("#F6AA20", "#F6AA20", "#F6AA20", "#FFFFFFFF"))
            val updatedLottieJsonTwo =
                updateLayerColors(lottieJson, listOf("#D8532A", "#D8532A", "#D8532A", "#FFFFFFFF"))
            val updatedLottieJsonThree =
                updateLayerColors(lottieJson, listOf("#AE2A4F", "#AE2A4F", "#AE2A4F", "#FFFFFFFF"))
            val updatedLottieJsonFour =
                updateLayerColors(lottieJson, listOf("#066877", "#066877", "#066877", "#FFFFFFFF"))

            // Set the updated JSON to the Lottie animation view
            val compositionOne = LottieCompositionFactory.fromJsonStringSync(
                updatedLottieJsonOne,
                "custom_cache_key_one"
            )
            val compositionTwo = LottieCompositionFactory.fromJsonStringSync(
                updatedLottieJsonTwo,
                "custom_cache_key_two"
            )
            val compositionThree = LottieCompositionFactory.fromJsonStringSync(
                updatedLottieJsonThree,
                "custom_cache_key_three"
            )
            val compositionFour = LottieCompositionFactory.fromJsonStringSync(
                updatedLottieJsonFour,
                "custom_cache_key_four"
            )
            if (compositionOne != null) {
                lavOne.setComposition(compositionOne.value!!)
                lavOne.repeatCount = LottieDrawable.INFINITE
                lavOne.playAnimation()
            }
            if (compositionTwo != null) {
                lavTwo.setComposition(compositionTwo.value!!)
                lavTwo.repeatCount = LottieDrawable.INFINITE
                lavTwo.playAnimation()
            }
            if (compositionThree != null) {
                lavThree.setComposition(compositionThree.value!!)
                lavThree.repeatCount = LottieDrawable.INFINITE
                lavThree.playAnimation()
            }
            if (compositionFour != null) {
                lavFour.setComposition(compositionFour.value!!)
                lavFour.repeatCount = LottieDrawable.INFINITE
                lavFour.playAnimation()
            }*/
            lavOne.setAnimation(R.raw.arrow_animation)
            lavTwo.setAnimation(R.raw.arrow_animation)
            lavThree.setAnimation(R.raw.arrow_animation)
            lavFour.setAnimation(R.raw.arrow_animation)
            // Change colors using dynamic properties API

            val colorOne = "#F8A825"
            val colorTwo = "#D8532A"
            val colorThree = "#AE2A4F"
            val colorFour = "#066877"

            val layerIndices = listOf("Layer 0", "Layer 1", "Layer 2")

// Loop through each layer index and apply the color callback
            for (layerIndex in layerIndices) {
                val keyPath = KeyPath(layerIndex, "Group 1", "Fill 1")
                lavOne.addValueCallback(
                    keyPath,
                    LottieProperty.COLOR
                ) { Color.parseColor(colorOne) }
            }
            for (layerIndex in layerIndices) {
                val keyPath = KeyPath(layerIndex, "Group 1", "Fill 1")
                lavTwo.addValueCallback(
                    keyPath,
                    LottieProperty.COLOR
                ) { Color.parseColor(colorTwo) }
            }
            for (layerIndex in layerIndices) {
                val keyPath = KeyPath(layerIndex, "Group 1", "Fill 1")
                lavThree.addValueCallback(
                    keyPath,
                    LottieProperty.COLOR
                ) { Color.parseColor(colorThree) }
            }
            for (layerIndex in layerIndices) {
                val keyPath = KeyPath(layerIndex, "Group 1", "Fill 1")
                lavFour.addValueCallback(
                    keyPath,
                    LottieProperty.COLOR
                ) { Color.parseColor(colorFour) }
            }

            // Optionally play animations
            lavOne.playAnimation()
            lavTwo.playAnimation()
            lavThree.playAnimation()
            lavFour.playAnimation()
        }
    }

    /*private fun readRawResource(resourceId: Int): String {
        val inputStream = resources.openRawResource(resourceId)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        return bufferedReader.use { it.readText() }
    }

    private fun updateLayerColors(json: String, colors: List<String>): String {
        val jsonObject = JSONObject(json)
        val layers = jsonObject.getJSONArray("layers")

        for (i in 0 until layers.length()) {
            val layer = layers.getJSONObject(i)
            val shapes = layer.getJSONArray("shapes")

            for (j in 0 until shapes.length()) {
                val shape = shapes.getJSONObject(j)
                val arrayIt = shape.getJSONArray("it")

                for (k in 0 until arrayIt.length()) {
                    val it = arrayIt.getJSONObject(k)
                    if (it.has("ty") && it.getString("ty") == "fl") {
                        val color = colors[i % colors.size]
                        val colorArray = colorToLottieColor(color)

                        val c = it.getJSONObject("c")
                        c.put("k", colorArray)
                    }

                }
            }
        }

        return jsonObject.toString()
    }

    private fun colorToLottieColor(color: String): JSONArray {
        val r = Integer.parseInt(color.substring(1, 3), 16) / 255f
        val g = Integer.parseInt(color.substring(3, 5), 16) / 255f
        val b = Integer.parseInt(color.substring(5, 7), 16) / 255f

        return JSONArray(listOf(r, g, b, 1))
    }*/

    private fun setImageView(imageView: ImageView, imageViewTint: ImageView, url: String) {

        imageView.load(url) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
        imageViewTint.load(url) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
    }

    private fun setImageViewGLide(
        imageView: ImageView,
        imageViewTint: ImageView,
        url: String,
        tintColor: String
    ) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(imageView)
        Glide.with(imageViewTint.context)
            .load(url)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(imageViewTint)
        val color = Color.parseColor("#$tintColor")
        imageViewTint.imageTintList = ColorStateList.valueOf(color)
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                ivBack.id -> {
                    findNavController().navigate(R.id.action_taskThreeFragment_to_mainFragment)
                }

                ivGlideVector0.id -> {
                    openDialog(ivGlideVector0, root)
                }

                ivGlideVector1.id -> {
                    openDialog(ivGlideVector1, root)
                }

                ivGlideVector2.id -> {
                    openDialog(ivGlideVector2, root)
                }

                ivGlideVector3.id -> {
                    openDialog(ivGlideVector3, root)
                }

                ivGlideVector4.id -> {
                    openDialog(ivGlideVector4, root)
                }

                ivGlideVector5.id -> {
                    openDialog(ivGlideVector5, root)
                }

                ivGlideVectorTint0.id -> {
                    openDialog(ivGlideVectorTint0, root)
                }

                ivGlideVectorTint1.id -> {
                    openDialog(ivGlideVectorTint1, root)
                }

                ivGlideVectorTint2.id -> {
                    openDialog(ivGlideVectorTint2, root)
                }

                ivGlideVectorTint3.id -> {
                    openDialog(ivGlideVectorTint3, root)
                }

                ivGlideVectorTint4.id -> {
                    openDialog(ivGlideVectorTint4, root)
                }

                ivGlideVectorTint5.id -> {
                    openDialog(ivGlideVectorTint5, root)
                }
            }
        }
    }

    private fun openDialog(anchorView: View, root: ConstraintLayout) {

        val dialogBinding =
            ItemDialogBinding.inflate(LayoutInflater.from(context))

        val anchorLocation = IntArray(2)
        anchorView.getLocationInWindow(anchorLocation)
        val x = anchorLocation[0]
        val y = anchorLocation[1]
        println("Dialog position: $x, $y")

        val popupWindow = PopupWindow(
            dialogBinding.root,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        popupWindow.isOutsideTouchable = true
        popupWindow.showAtLocation(
            root,
            Gravity.START or Gravity.TOP,
            x,
            y + (anchorView.height / 2)
        )

        dialogBinding.root.setOnClickListener {
            popupWindow.dismiss()
        }

        val overlay = View(requireActivity())
        overlay.setBackgroundColor(resources.getColor(R.color.transparent_black_mid, null))
        root.addView(overlay)
        overlay.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.setOnDismissListener {
            overlay.visibility = GONE
        }

    }

}