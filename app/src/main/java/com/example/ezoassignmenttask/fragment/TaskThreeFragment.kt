package com.example.ezoassignmenttask.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.decode.SvgDecoder
import coil.load
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

        val url0 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/aa.svg"
        val url1 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/adobe.svg"
        val url2 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/alphachannel.svg"
        val url3 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/android.svg"
        val url4 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/atom.svg"
//        val url5 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/bozo.svg"
//        val url5 = "https://svgshare.com/i/1354.svg"
        val url5 = "https://i.ibb.co/FzPN58P/bill-new-3x.png"
        binding.apply {
            setImageView(ivGlideVector0, ivGlideVectorTint0, url0)
            setImageView(ivGlideVector1, ivGlideVectorTint1, url1)
            setImageView(ivGlideVector2, ivGlideVectorTint2, url2)
            setImageView(ivGlideVector3, ivGlideVectorTint3, url3)
            setImageViewGLide(ivGlideVector4, ivGlideVectorTint4, url4)
            setImageView(ivGlideVector4, ivGlideVectorTint4, url4)
            setImageViewGLide(ivGlideVector5, ivGlideVectorTint5, url5)
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
        }
    }


    private fun setImageView(imageView: ImageView, imageViewTint: ImageView, url: String) {

        imageView.load(url) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
        imageViewTint.load(url) {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
    }

    private fun setImageViewGLide(imageView: ImageView, imageViewTint: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(imageView)
        Glide.with(imageViewTint.context)
            .load(url)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(imageViewTint)
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