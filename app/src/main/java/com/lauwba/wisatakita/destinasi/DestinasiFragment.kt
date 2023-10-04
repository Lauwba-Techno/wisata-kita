package com.lauwba.wisatakita.destinasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.lauwba.wisatakita.SpashScreenActivity
import com.lauwba.wisatakita.databinding.FragmentDestinasiBinding
import com.google.android.gms.maps.model.LatLng
import java.util.zip.Inflater
import kotlin.math.abs


class DestinasiFragment : Fragment() {
    private var _binding: FragmentDestinasiBinding? = null
    private val binding get() = _binding!!

    private val destinationImage = arrayListOf(
        "https://lh3.googleusercontent.com/proxy/XwMFvRht_5LJy2nHRXPZ_pz_SCWTlLh1BBgEs1v0ZAjjuxOIYBmTRqh0mK45UWxEC8ug13F8ks9g3RaH7KMY_XsibDrMBpVaSy_j0uzi6qf9QXoXH8EU4hrR6zCKuoK6W9oHjG_Yx0L_qIOuek0_LzXpd1AmaJw=w1000-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipMEosqiOfvW7aB8GROmk874_HfLhwhT_z1RphPi=w1000-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipNzTZjNeln6WUrc1sWklEZCGMRQhDyU3jxHY0uw=s677-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipPsmfiRY32YKp80kaA-OMq8E8u9agDh9ZnUcDtq=w1000-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipMYljwO4dYe4vIo27L0JrWoOlILu8UfWjnzLzYr=w1000-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipNkQJXDmUSTnJ0kv5XJgskvrUgJollFT4UkyhYD=w1000-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipPhIc06sZYJIp423XjsgxdQEJv5X5mgEouJ2LHf=w1000-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipPknoGMr9y7h_ALXLkQZj1i9UsJU0o2O-tazF3R=w1156-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipPA2P3xj2NgPsA20qrcZ4DUR0wynFDkVXK6txTg=w1156-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipNzLkzvZR9REtZNT_god-NAXlN5nLojMpGKny73=w1150-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipM2xZoOLys8P6KdlnXA2jTqyNba5_A13ylCAbnB=w1150-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipO5H-Qb-Eh25PAF4f_8a0nGETqMtQvMVPnQJO-w=w1150-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipOiNuhhoRifZTVDuPha9QxigQb-CFXP0AEAZMeb=w1156-h780-k-no",
        "https://lh5.googleusercontent.com/p/AF1QipNbnCnj1y_ZPaF188N9a0oXgleAaHk6v6TtM0Vh=w1150-h780-k-no"
    )

    private val destinationName = arrayListOf(
        "Candi Prambanan",
        "Taman Sari",
        "Tugu Jogja",
        "Taman Nasional Gunung Merapi",
        "Yogyakarta International Airport",
        "Pantai Parangtritis",
        "Air Terjun Sri Gethuk",
        "Pantai Indrayanti",
        "Pantai Pok Tunggal",
        "Jl. Malioboro",
        "Pasar Beringharjo",
        "Ratu Boko",
        "Kebun Buah Mangunan",
        "Hutan Mangrove Wana Tirta"
    )
    private val destinationLocation = arrayListOf(
        "Sleman",
        "Kota Yogyakarta",
        "Kota Yogyakarta",
        "Sleman",
        "Kulon Progo",
        "Bantul",
        "Gunung Kidul",
        "Gunung Kidul",
        "Gunung Kidul",
        "Kota Yogyakarta",
        "Kota Yogyakarta",
        "Sleman",
        "Bantul",
        "Kulon Progo"
    )
    private val destinationLatLng = arrayListOf(
        LatLng(-7.7520211, 110.4925099),
        LatLng(-7.809798, 110.359054),
        LatLng(-7.7828609, 110.3583181),
        LatLng(-7.5407175, 110.4457241),
        LatLng(-7.900302, 110.0569203),
        LatLng(-8.0261393, 110.3351046),
        LatLng(-7.9428521, 110.4871372),
        LatLng(-8.1507833, 110.6103773),
        LatLng(-8.1554992, 110.6128602),
        LatLng(-7.7926306, 110.3658442),
        LatLng(-7.798789, 110.3652543),
        LatLng(-7.7705363, 110.487227),
        LatLng(-7.9413665, 110.4225458),
        LatLng(-7.89485, 110.0209858)
    )

    private val destinationRating = arrayListOf(
        4.5,
        4.6,
        4.8,
        4.4,
        4.6,
        4.5,
        4.4,
        4.5,
        4.6,
        4.7,
        4.5,
        4.6,
        4.6,
        4.1
    )

    private val destinationDescription = arrayListOf(
        "Candi Prambanan adalah bangunan candi bercorak agama Hindu terbesar di Indonesia yang dibangun pada abad ke-9 Masehi.",
        "Taman Sari adalah situs bekas taman atau kebun istana Keraton Ngayogyakarta Hadiningrat, yang dapat dibandingkan dengan Taman Sari Surakarta dan Kebun Raya Bogor sebagai kebun Istana Bogor.",
        "Tugu Yogyakarta adalah sebuah tugu atau monumen yang sering dipakai sebagai simbol atau lambang dari Kota Yogyakarta, Daerah Istimewa Yogyakarta.",
        "Taman Nasional Gunung Merapi adalah sebuah taman nasional yang terletak di Jawa bagian tengah. Secara administrasi kepemerintahan, wilayah taman nasional ini masuk ke dalam wilayah dua provinsi, yakni Jawa Tengah dan Yogyakarta.",
        "Bandar Udara Internasional Yogyakarta atau Yogyakarta Internasional Airport (YIA) adalah bandar udara yang terletak di Kecamatan Temon, Kabupaten Kulon Progo, Provinsi DI Yogyakarta.",
        "Pantai Parangtritis adalah tempat wisata yang terletak di Kalurahan Parangtritis, Kapanéwon Kretek, Kabupaten Bantul, Daerah Istimewa Yogyakarta. Jaraknya kurang lebih 27 km dari pusat kota. Pantai ini menjadi salah satu destinasi wisata terkenal di Yogyakarta dan telah menjadi ikon pariwisata di Yogyakarta. ",
        "Air Terjun Sri Gethuk merupakan salah satu objek wisata alam yang terletak di Kecamatan Playen, Kabupaten Gunungkidul, Yogyakarta. Air terjun ini berada di tepi Sungai Oyo sehingga untuk menikmatinya harus menyelusuri sungai dengan rakit.",
        "Pantai kecil berpasir putih yang dikelilingi pepohonan dan bebatuan besar, serta beragam restoran & pertokoan.",
        "Pantai pasir putih yang dikelilingi tebing & pepohonan, dengan pohon ikonis yang populer untuk tempat foto. ",
        "Jalan Malioboro adalah salah satu kawasan jalan dari tiga jalan di Kota Yogyakarta yang membentang dari Tugu Yogyakarta hingga ke persimpangan Titik Nol Kilometer Yogyakarta. Secara keseluruhan, kawasan Malioboro terdiri atas Jalan Margo Utomo, Jalan Malioboro, dan Jalan Margo Mulyo.",
        "Pasar Beringharjo menjadi sebuah bagian dari Malioboro yang sayang untuk dilewatkan. Bagaimana tidak, pasar ini telah menjadi pusat kegiatan ekonomi selama ratusan tahun dan keberadaannya mempunyai makna filosofis. Pasar yang telah berkali-kali dipugar ini melambangkan satu tahapan kehidupan manusia yang masih berkutat dengan pemenuhan kebutuhan ekonominya.",
        "Ratu Boko adalah situs purbakala yang merupakan kompleks sejumlah sisa bangunan yang berada kira-kira 3 km di sebelah selatan dari kompleks Candi Prambanan, 18 km sebelah timur Kota Yogyakarta atau 50 km barat daya Kota Surakarta, Jawa Tengah, lebih tepatnya di kalurahan Bokoharjo, kapanéwon Prambanan, kabupaten Sleman, Daerah Istimewa Yogyakarta. Situs Ratu Boko terletak di sebuah bukit pada ketinggian 196 meter dari permukaan laut.",
        "Kebun buah di puncak bukit yang menawan dengan beragam pohon buah di tengah pemandangan luas di wilayah ini.",
        "Tanaman Mangrove yang hanya bisa tumbuh di muara sungai, perlu dijaga dan dilestarikan keberadaanya. Salah satu tokoh penjaga dan penggerak dalam pelestarian dan penanaman mangrove adalah Mbah Warso suwito, beliau adalah warga Pasir Mendit, Kalurahan Jangkaran, Kapanewon Temon, Kabupaten Kulon Progo yang sudah cukup lama berkecimpung di dunia mangrove.",
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listDestinasi = arrayListOf<Destinasi>()

        destinationImage.mapIndexed { index, img ->
            val destinasi = Destinasi(
                namaDestinasi = destinationName[index],
                rating = destinationRating[index],
                fotoDestinasi = img,
                coordinate = destinationLatLng[index],
                location = destinationLocation[index],
                deskripsiDestinasi = destinationDescription[index]
            )
            listDestinasi.add(destinasi)


        }
        val adapter = DestinasiAdapter(listDestinasi) {
            val detailActivity = Intent(requireActivity(), DetailActivity::class.java)
            detailActivity.putExtra("lat", it.coordinate.latitude)
            detailActivity.putExtra("lng", it.coordinate.longitude)
            detailActivity.putExtra("fotoDestinasi", it.fotoDestinasi)
            detailActivity.putExtra("rating", it.rating)
            detailActivity.putExtra("namaDestinasi", it.namaDestinasi)
            detailActivity.putExtra("deskripsiDestinasi", it.deskripsiDestinasi)
            detailActivity.putExtra("location", it.location)
            startActivity(detailActivity)
        }

        binding.viewPagerSlider.adapter = adapter
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(20))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f

        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

