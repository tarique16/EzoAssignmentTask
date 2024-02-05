package com.example.ezoassignmenttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.FragmentTaskThreeBinding

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

        val imageLoader = ImageLoader.Builder(requireActivity())
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val url0 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/aa.svg"
        val url1 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/adobe.svg"
        val url2 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/alphachannel.svg"
        val url3 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/android.svg"
        val url4 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/atom.svg"
        val url5 = "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/bozo.svg"
        binding.apply {
            setImageView(ivGlideVector0, ivGlideVectorTint0, url0)
            setImageView(ivGlideVector1, ivGlideVectorTint1, url1)
            setImageView(ivGlideVector2, ivGlideVectorTint2, url2)
            setImageView(ivGlideVector3, ivGlideVectorTint3, url3)
            setImageView(ivGlideVector4, ivGlideVectorTint4, url4)
            setImageView(ivGlideVector5, ivGlideVectorTint5, url5)
            ivBack.setOnClickListener(this@TaskThreeFragment)
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

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0?.id) {
                ivBack.id -> {
                    findNavController().navigate(R.id.action_taskThreeFragment_to_mainFragment)
                }
            }
        }
    }
}