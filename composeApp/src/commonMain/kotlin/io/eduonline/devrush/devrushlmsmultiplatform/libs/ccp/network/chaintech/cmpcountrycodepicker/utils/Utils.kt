package network.chaintech.cmpcountrycodepicker.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import devrush_multiplatform.composeapp.generated.resources.flag_ad
import devrush_multiplatform.composeapp.generated.resources.flag_ae
import devrush_multiplatform.composeapp.generated.resources.flag_af
import devrush_multiplatform.composeapp.generated.resources.flag_ag
import devrush_multiplatform.composeapp.generated.resources.flag_ai
import devrush_multiplatform.composeapp.generated.resources.flag_al
import devrush_multiplatform.composeapp.generated.resources.flag_am
import devrush_multiplatform.composeapp.generated.resources.flag_american_samoa
import devrush_multiplatform.composeapp.generated.resources.flag_ao
import devrush_multiplatform.composeapp.generated.resources.flag_aq
import devrush_multiplatform.composeapp.generated.resources.flag_ar
import devrush_multiplatform.composeapp.generated.resources.flag_at
import devrush_multiplatform.composeapp.generated.resources.flag_au
import devrush_multiplatform.composeapp.generated.resources.flag_aw
import devrush_multiplatform.composeapp.generated.resources.flag_ax
import devrush_multiplatform.composeapp.generated.resources.flag_az
import devrush_multiplatform.composeapp.generated.resources.flag_ba
import devrush_multiplatform.composeapp.generated.resources.flag_bb
import devrush_multiplatform.composeapp.generated.resources.flag_bd
import devrush_multiplatform.composeapp.generated.resources.flag_be
import devrush_multiplatform.composeapp.generated.resources.flag_bf
import devrush_multiplatform.composeapp.generated.resources.flag_bg
import devrush_multiplatform.composeapp.generated.resources.flag_bh
import devrush_multiplatform.composeapp.generated.resources.flag_bi
import devrush_multiplatform.composeapp.generated.resources.flag_bj
import devrush_multiplatform.composeapp.generated.resources.flag_bl
import devrush_multiplatform.composeapp.generated.resources.flag_bm
import devrush_multiplatform.composeapp.generated.resources.flag_bn
import devrush_multiplatform.composeapp.generated.resources.flag_bo
import devrush_multiplatform.composeapp.generated.resources.flag_br
import devrush_multiplatform.composeapp.generated.resources.flag_bs
import devrush_multiplatform.composeapp.generated.resources.flag_bt
import devrush_multiplatform.composeapp.generated.resources.flag_bw
import devrush_multiplatform.composeapp.generated.resources.flag_by
import devrush_multiplatform.composeapp.generated.resources.flag_bz
import devrush_multiplatform.composeapp.generated.resources.flag_ca
import devrush_multiplatform.composeapp.generated.resources.flag_cc
import devrush_multiplatform.composeapp.generated.resources.flag_cd
import devrush_multiplatform.composeapp.generated.resources.flag_cf
import devrush_multiplatform.composeapp.generated.resources.flag_cg
import devrush_multiplatform.composeapp.generated.resources.flag_ch
import devrush_multiplatform.composeapp.generated.resources.flag_ci
import devrush_multiplatform.composeapp.generated.resources.flag_ck
import devrush_multiplatform.composeapp.generated.resources.flag_cl
import devrush_multiplatform.composeapp.generated.resources.flag_cm
import devrush_multiplatform.composeapp.generated.resources.flag_cn
import devrush_multiplatform.composeapp.generated.resources.flag_co
import devrush_multiplatform.composeapp.generated.resources.flag_cr
import devrush_multiplatform.composeapp.generated.resources.flag_cu
import devrush_multiplatform.composeapp.generated.resources.flag_cv
import devrush_multiplatform.composeapp.generated.resources.flag_cw
import devrush_multiplatform.composeapp.generated.resources.flag_cx
import devrush_multiplatform.composeapp.generated.resources.flag_cy
import devrush_multiplatform.composeapp.generated.resources.flag_cz
import devrush_multiplatform.composeapp.generated.resources.flag_de
import devrush_multiplatform.composeapp.generated.resources.flag_dj
import devrush_multiplatform.composeapp.generated.resources.flag_dk
import devrush_multiplatform.composeapp.generated.resources.flag_dm
import devrush_multiplatform.composeapp.generated.resources.flag_dz
import devrush_multiplatform.composeapp.generated.resources.flag_ec
import devrush_multiplatform.composeapp.generated.resources.flag_ee
import devrush_multiplatform.composeapp.generated.resources.flag_eg
import devrush_multiplatform.composeapp.generated.resources.flag_er
import devrush_multiplatform.composeapp.generated.resources.flag_es
import devrush_multiplatform.composeapp.generated.resources.flag_et
import devrush_multiplatform.composeapp.generated.resources.flag_fi
import devrush_multiplatform.composeapp.generated.resources.flag_fj
import devrush_multiplatform.composeapp.generated.resources.flag_fk
import devrush_multiplatform.composeapp.generated.resources.flag_fm
import devrush_multiplatform.composeapp.generated.resources.flag_fo
import devrush_multiplatform.composeapp.generated.resources.flag_fr
import devrush_multiplatform.composeapp.generated.resources.flag_ga
import devrush_multiplatform.composeapp.generated.resources.flag_gb
import devrush_multiplatform.composeapp.generated.resources.flag_gd
import devrush_multiplatform.composeapp.generated.resources.flag_ge
import devrush_multiplatform.composeapp.generated.resources.flag_gf
import devrush_multiplatform.composeapp.generated.resources.flag_gh
import devrush_multiplatform.composeapp.generated.resources.flag_gi
import devrush_multiplatform.composeapp.generated.resources.flag_gl
import devrush_multiplatform.composeapp.generated.resources.flag_gm
import devrush_multiplatform.composeapp.generated.resources.flag_gn
import devrush_multiplatform.composeapp.generated.resources.flag_gp
import devrush_multiplatform.composeapp.generated.resources.flag_gq
import devrush_multiplatform.composeapp.generated.resources.flag_gr
import devrush_multiplatform.composeapp.generated.resources.flag_gt
import devrush_multiplatform.composeapp.generated.resources.flag_gu
import devrush_multiplatform.composeapp.generated.resources.flag_gw
import devrush_multiplatform.composeapp.generated.resources.flag_gy
import devrush_multiplatform.composeapp.generated.resources.flag_hk
import devrush_multiplatform.composeapp.generated.resources.flag_hn
import devrush_multiplatform.composeapp.generated.resources.flag_hr
import devrush_multiplatform.composeapp.generated.resources.flag_ht
import devrush_multiplatform.composeapp.generated.resources.flag_hu
import devrush_multiplatform.composeapp.generated.resources.flag_ic_do
import devrush_multiplatform.composeapp.generated.resources.flag_iceland
import devrush_multiplatform.composeapp.generated.resources.flag_id
import devrush_multiplatform.composeapp.generated.resources.flag_ie
import devrush_multiplatform.composeapp.generated.resources.flag_il
import devrush_multiplatform.composeapp.generated.resources.flag_im
import devrush_multiplatform.composeapp.generated.resources.flag_india
import devrush_multiplatform.composeapp.generated.resources.flag_io
import devrush_multiplatform.composeapp.generated.resources.flag_iq
import devrush_multiplatform.composeapp.generated.resources.flag_ir
import devrush_multiplatform.composeapp.generated.resources.flag_it
import devrush_multiplatform.composeapp.generated.resources.flag_je
import devrush_multiplatform.composeapp.generated.resources.flag_jm
import devrush_multiplatform.composeapp.generated.resources.flag_jo
import devrush_multiplatform.composeapp.generated.resources.flag_jp
import devrush_multiplatform.composeapp.generated.resources.flag_ke
import devrush_multiplatform.composeapp.generated.resources.flag_kg
import devrush_multiplatform.composeapp.generated.resources.flag_kh
import devrush_multiplatform.composeapp.generated.resources.flag_ki
import devrush_multiplatform.composeapp.generated.resources.flag_km
import devrush_multiplatform.composeapp.generated.resources.flag_kn
import devrush_multiplatform.composeapp.generated.resources.flag_kp
import devrush_multiplatform.composeapp.generated.resources.flag_kr
import devrush_multiplatform.composeapp.generated.resources.flag_kw
import devrush_multiplatform.composeapp.generated.resources.flag_ky
import devrush_multiplatform.composeapp.generated.resources.flag_kz
import devrush_multiplatform.composeapp.generated.resources.flag_la
import devrush_multiplatform.composeapp.generated.resources.flag_lb
import devrush_multiplatform.composeapp.generated.resources.flag_lc
import devrush_multiplatform.composeapp.generated.resources.flag_li
import devrush_multiplatform.composeapp.generated.resources.flag_lk
import devrush_multiplatform.composeapp.generated.resources.flag_lr
import devrush_multiplatform.composeapp.generated.resources.flag_ls
import devrush_multiplatform.composeapp.generated.resources.flag_lt
import devrush_multiplatform.composeapp.generated.resources.flag_lu
import devrush_multiplatform.composeapp.generated.resources.flag_lv
import devrush_multiplatform.composeapp.generated.resources.flag_ly
import devrush_multiplatform.composeapp.generated.resources.flag_ma
import devrush_multiplatform.composeapp.generated.resources.flag_mc
import devrush_multiplatform.composeapp.generated.resources.flag_md
import devrush_multiplatform.composeapp.generated.resources.flag_me
import devrush_multiplatform.composeapp.generated.resources.flag_mf
import devrush_multiplatform.composeapp.generated.resources.flag_mg
import devrush_multiplatform.composeapp.generated.resources.flag_mh
import devrush_multiplatform.composeapp.generated.resources.flag_mk
import devrush_multiplatform.composeapp.generated.resources.flag_ml
import devrush_multiplatform.composeapp.generated.resources.flag_mm
import devrush_multiplatform.composeapp.generated.resources.flag_mn
import devrush_multiplatform.composeapp.generated.resources.flag_mo
import devrush_multiplatform.composeapp.generated.resources.flag_mp
import devrush_multiplatform.composeapp.generated.resources.flag_mq
import devrush_multiplatform.composeapp.generated.resources.flag_mr
import devrush_multiplatform.composeapp.generated.resources.flag_ms
import devrush_multiplatform.composeapp.generated.resources.flag_mt
import devrush_multiplatform.composeapp.generated.resources.flag_mu
import devrush_multiplatform.composeapp.generated.resources.flag_mv
import devrush_multiplatform.composeapp.generated.resources.flag_mw
import devrush_multiplatform.composeapp.generated.resources.flag_mx
import devrush_multiplatform.composeapp.generated.resources.flag_my
import devrush_multiplatform.composeapp.generated.resources.flag_mz
import devrush_multiplatform.composeapp.generated.resources.flag_na
import devrush_multiplatform.composeapp.generated.resources.flag_nc
import devrush_multiplatform.composeapp.generated.resources.flag_ne
import devrush_multiplatform.composeapp.generated.resources.flag_nf
import devrush_multiplatform.composeapp.generated.resources.flag_ng
import devrush_multiplatform.composeapp.generated.resources.flag_ni
import devrush_multiplatform.composeapp.generated.resources.flag_nl
import devrush_multiplatform.composeapp.generated.resources.flag_no
import devrush_multiplatform.composeapp.generated.resources.flag_np
import devrush_multiplatform.composeapp.generated.resources.flag_nr
import devrush_multiplatform.composeapp.generated.resources.flag_nu
import devrush_multiplatform.composeapp.generated.resources.flag_nz
import devrush_multiplatform.composeapp.generated.resources.flag_om
import devrush_multiplatform.composeapp.generated.resources.flag_pa
import devrush_multiplatform.composeapp.generated.resources.flag_pe
import devrush_multiplatform.composeapp.generated.resources.flag_pf
import devrush_multiplatform.composeapp.generated.resources.flag_pg
import devrush_multiplatform.composeapp.generated.resources.flag_ph
import devrush_multiplatform.composeapp.generated.resources.flag_pk
import devrush_multiplatform.composeapp.generated.resources.flag_pl
import devrush_multiplatform.composeapp.generated.resources.flag_pm
import devrush_multiplatform.composeapp.generated.resources.flag_pn
import devrush_multiplatform.composeapp.generated.resources.flag_pr
import devrush_multiplatform.composeapp.generated.resources.flag_ps
import devrush_multiplatform.composeapp.generated.resources.flag_pt
import devrush_multiplatform.composeapp.generated.resources.flag_pw
import devrush_multiplatform.composeapp.generated.resources.flag_py
import devrush_multiplatform.composeapp.generated.resources.flag_qa
import devrush_multiplatform.composeapp.generated.resources.flag_re
import devrush_multiplatform.composeapp.generated.resources.flag_ro
import devrush_multiplatform.composeapp.generated.resources.flag_rs
import devrush_multiplatform.composeapp.generated.resources.flag_rw
import devrush_multiplatform.composeapp.generated.resources.flag_sa
import devrush_multiplatform.composeapp.generated.resources.flag_sb
import devrush_multiplatform.composeapp.generated.resources.flag_sc
import devrush_multiplatform.composeapp.generated.resources.flag_sd
import devrush_multiplatform.composeapp.generated.resources.flag_se
import devrush_multiplatform.composeapp.generated.resources.flag_sg
import devrush_multiplatform.composeapp.generated.resources.flag_sh
import devrush_multiplatform.composeapp.generated.resources.flag_si
import devrush_multiplatform.composeapp.generated.resources.flag_sk
import devrush_multiplatform.composeapp.generated.resources.flag_sl
import devrush_multiplatform.composeapp.generated.resources.flag_sm
import devrush_multiplatform.composeapp.generated.resources.flag_sn
import devrush_multiplatform.composeapp.generated.resources.flag_so
import devrush_multiplatform.composeapp.generated.resources.flag_sr
import devrush_multiplatform.composeapp.generated.resources.flag_ss
import devrush_multiplatform.composeapp.generated.resources.flag_st
import devrush_multiplatform.composeapp.generated.resources.flag_sv
import devrush_multiplatform.composeapp.generated.resources.flag_sx
import devrush_multiplatform.composeapp.generated.resources.flag_sy
import devrush_multiplatform.composeapp.generated.resources.flag_sz
import devrush_multiplatform.composeapp.generated.resources.flag_tc
import devrush_multiplatform.composeapp.generated.resources.flag_td
import devrush_multiplatform.composeapp.generated.resources.flag_tg
import devrush_multiplatform.composeapp.generated.resources.flag_th
import devrush_multiplatform.composeapp.generated.resources.flag_tj
import devrush_multiplatform.composeapp.generated.resources.flag_tk
import devrush_multiplatform.composeapp.generated.resources.flag_tl
import devrush_multiplatform.composeapp.generated.resources.flag_tm
import devrush_multiplatform.composeapp.generated.resources.flag_tn
import devrush_multiplatform.composeapp.generated.resources.flag_to
import devrush_multiplatform.composeapp.generated.resources.flag_tr
import devrush_multiplatform.composeapp.generated.resources.flag_tt
import devrush_multiplatform.composeapp.generated.resources.flag_tv
import devrush_multiplatform.composeapp.generated.resources.flag_tw
import devrush_multiplatform.composeapp.generated.resources.flag_tz
import devrush_multiplatform.composeapp.generated.resources.flag_ua
import devrush_multiplatform.composeapp.generated.resources.flag_ug
import devrush_multiplatform.composeapp.generated.resources.flag_us
import devrush_multiplatform.composeapp.generated.resources.flag_uy
import devrush_multiplatform.composeapp.generated.resources.flag_uz
import devrush_multiplatform.composeapp.generated.resources.flag_va
import devrush_multiplatform.composeapp.generated.resources.flag_vc
import devrush_multiplatform.composeapp.generated.resources.flag_ve
import devrush_multiplatform.composeapp.generated.resources.flag_vg
import devrush_multiplatform.composeapp.generated.resources.flag_vi
import devrush_multiplatform.composeapp.generated.resources.flag_vn
import devrush_multiplatform.composeapp.generated.resources.flag_vu
import devrush_multiplatform.composeapp.generated.resources.flag_wf
import devrush_multiplatform.composeapp.generated.resources.flag_ws
import devrush_multiplatform.composeapp.generated.resources.flag_xk
import devrush_multiplatform.composeapp.generated.resources.flag_ye
import devrush_multiplatform.composeapp.generated.resources.flag_yt
import devrush_multiplatform.composeapp.generated.resources.flag_za
import devrush_multiplatform.composeapp.generated.resources.flag_zm
import devrush_multiplatform.composeapp.generated.resources.flag_zw
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.flag_ru
import network.chaintech.cmpcountrycodepicker.model.CountryDetails

internal object Utils {

    fun searchCountry(
        searchStr: String,
        countriesList: List<CountryDetails>,
    ): List<CountryDetails> {
        return countriesList.filter {
            it.countryName.contains(searchStr, ignoreCase = true)
                    || it.countryPhoneNumberCode.contains(searchStr, ignoreCase = true)
                    || it.countryCode.contains(searchStr, ignoreCase = true)
        }
    }

    fun getCountryFromCountryCode(
        countryCode: String,
        countriesList: List<CountryDetails>
    ): CountryDetails {
        return countriesList.single { it.countryCode == countryCode }
    }

    fun getCountryList(): List<CountryDetails> = listOf(
        CountryDetails(
            countryCode = "ad",
            "+376",
            "Andorra",
            Res.drawable.flag_ad,
        ),
        CountryDetails(
            countryCode = "ae",
            "+971",
            "United Arab Emirates (UAE)",
            Res.drawable.flag_ae,
        ),
        CountryDetails(
            countryCode = "af",
            "+93",
            "Afghanistan",
            Res.drawable.flag_af,
        ),
        CountryDetails(
            countryCode = "ag",
            "+1",
            "Antigua and Barbuda",
            Res.drawable.flag_ag,
        ),
        CountryDetails(
            "ai",
            "+1",
            "Anguilla",
            Res.drawable.flag_ai,
        ),
        CountryDetails(
            "al",
            "+355",
            "Albania",
            Res.drawable.flag_al,
        ),
        CountryDetails(
            "am",
            "+374",
            "Armenia",
            Res.drawable.flag_am,
        ),
        CountryDetails(
            "ao",
            "+244",
            "Angola",
            Res.drawable.flag_ao,
        ),
        CountryDetails(
            "aq",
            "+672",
            "Antarctica",
            Res.drawable.flag_aq,
        ),
        CountryDetails(
            "ar",
            "+54",
            "Argentina",
            Res.drawable.flag_ar,
        ),
        CountryDetails(
            "as",
            "+1",
            "American Samoa",
            Res.drawable.flag_american_samoa
        ),
        CountryDetails(
            "at",
            "+43",
            "Austria",
            Res.drawable.flag_at,
        ),
        CountryDetails(
            "au",
            "+61",
            "Australia",
            Res.drawable.flag_au,
        ),
        CountryDetails(
            "aw",
            "+297",
            "Aruba",
            Res.drawable.flag_aw,
        ),
        CountryDetails(
            "ax",
            "+358",
            "Åland Islands",
            Res.drawable.flag_ax
        ),
        CountryDetails(
            "az",
            "+994",
            "Azerbaijan",
            Res.drawable.flag_az
        ),
        CountryDetails(
            "ba",
            "+387",
            "Bosnia And Herzegovina",
            Res.drawable.flag_ba
        ),
        CountryDetails(
            "bb",
            "+1",
            "Barbados",
            Res.drawable.flag_bb
        ),
        CountryDetails(
            "bd",
            "+880",
            "Bangladesh",
            Res.drawable.flag_bd
        ),
        CountryDetails(
            "be",
            "+32",
            "Belgium",
            Res.drawable.flag_be
        ),
        CountryDetails(
            "bf",
            "+226",
            "Burkina Faso",
            Res.drawable.flag_bf
        ),
        CountryDetails(
            "bg",
            "+359",
            "Bulgaria",
            Res.drawable.flag_bg
        ),
        CountryDetails(
            "bh",
            "+973",
            "Bahrain",
            Res.drawable.flag_bh
        ),
        CountryDetails(
            "bi",
            "+257",
            "Burundi",
            Res.drawable.flag_bi
        ),
        CountryDetails(
            "bj",
            "+229",
            "Benin",
            Res.drawable.flag_bj
        ),
        CountryDetails(
            "bl",
            "+590",
            "Saint Barthélemy",
            Res.drawable.flag_bl
        ),
        CountryDetails(
            "bm",
            "+1",
            "Bermuda",
            Res.drawable.flag_bm
        ),
        CountryDetails(
            "bn",
            "+673",
            "Brunei Darussalam",
            Res.drawable.flag_bn
        ),
        CountryDetails(
            "bo",
            "+591",
            "Bolivia, Plurinational State Of",
            Res.drawable.flag_bo
        ),
        CountryDetails(
            "br",
            "+55",
            "Brazil",
            Res.drawable.flag_br
        ),
        CountryDetails(
            "bs",
            "+1",
            "Bahamas",
            Res.drawable.flag_bs
        ),
        CountryDetails(
            "bt",
            "+975",
            "Bhutan",
            Res.drawable.flag_bt
        ),
        CountryDetails(
            "bw",
            "+267",
            "Botswana",
            Res.drawable.flag_bw
        ),
        CountryDetails(
            "by",
            "+375",
            "Belarus",
            Res.drawable.flag_by
        ),
        CountryDetails(
            "bz",
            "+501",
            "Belize",
            Res.drawable.flag_bz
        ),
        CountryDetails(
            "ca",
            "+1",
            "Canada",
            Res.drawable.flag_ca
        ),
        CountryDetails(
            "cc",
            "+61",
            "Cocos (keeling) Islands",
            Res.drawable.flag_cc
        ),
        CountryDetails(
            "cd",
            "+243",
            "Congo, The Democratic Republic Of The",
            Res.drawable.flag_cd
        ),
        CountryDetails(
            "cf",
            "+236",
            "Central African Republic",
            Res.drawable.flag_cf
        ),
        CountryDetails(
            "cg",
            "+242",
            "Congo",
            Res.drawable.flag_cg
        ),
        CountryDetails(
            "ch",
            "+41",
            "Switzerland",
            Res.drawable.flag_ch
        ),
        CountryDetails(
            "ci",
            "+225",
            "Côte D'ivoire",
            Res.drawable.flag_ci
        ),
        CountryDetails(
            "ck",
            "+682",
            "Cook Islands",
            Res.drawable.flag_ck
        ),
        CountryDetails(
            "cl",
            "+56",
            "Chile",
            Res.drawable.flag_cl
        ),
        CountryDetails(
            "cm",
            "+237",
            "Cameroon",
            Res.drawable.flag_cm
        ),
        CountryDetails(
            "cn",
            "+86",
            "China",
            Res.drawable.flag_cn
        ),
        CountryDetails(
            "co",
            "+57",
            "Colombia",
            Res.drawable.flag_co
        ),
        CountryDetails(
            "cr",
            "+506",
            "Costa Rica",
            Res.drawable.flag_cr
        ),
        CountryDetails(
            "cu",
            "+53",
            "Cuba",
            Res.drawable.flag_cu
        ),
        CountryDetails(
            "cv",
            "+238",
            "Cape Verde",
            Res.drawable.flag_cv
        ),
        CountryDetails(
            "cw",
            "+599",
            "Curaçao",
            Res.drawable.flag_cw
        ),
        CountryDetails(
            "cx",
            "+61",
            "Christmas Island",
            Res.drawable.flag_cx
        ),
        CountryDetails(
            "cy",
            "+357",
            "Cyprus",
            Res.drawable.flag_cy
        ),
        CountryDetails(
            "cz",
            "+420",
            "Czech Republic",
            Res.drawable.flag_cz
        ),
        CountryDetails(
            "de",
            "+49",
            "Germany",
            Res.drawable.flag_de
        ),
        CountryDetails(
            "dj",
            "+253",
            "Djibouti",
            Res.drawable.flag_dj
        ),
        CountryDetails(
            "dk",
            "+45",
            "Denmark",
            Res.drawable.flag_dk
        ),
        CountryDetails(
            "dm",
            "+1",
            "Dominica",
            Res.drawable.flag_dm
        ),
        CountryDetails(
            "do",
            "+1",
            "Dominican Republic",
            Res.drawable.flag_ic_do
        ),
        CountryDetails(
            "dz",
            "+213",
            "Algeria",
            Res.drawable.flag_dz
        ),
        CountryDetails(
            "ec",
            "+593",
            "Ecuador",
            Res.drawable.flag_ec
        ),
        CountryDetails(
            "ee",
            "+372",
            "Estonia",
            Res.drawable.flag_ee
        ),
        CountryDetails(
            "eg",
            "+20",
            "Egypt",
            Res.drawable.flag_eg
        ),
        CountryDetails(
            "er",
            "+291",
            "Eritrea",
            Res.drawable.flag_er
        ),
        CountryDetails(
            "es",
            "+34",
            "Spain",
            Res.drawable.flag_es
        ),
        CountryDetails(
            "et",
            "+251",
            "Ethiopia",
            Res.drawable.flag_et
        ),
        CountryDetails(
            "fi",
            "+358",
            "Finland",
            Res.drawable.flag_fi
        ),
        CountryDetails(
            "fj",
            "+679",
            "Fiji",
            Res.drawable.flag_fj
        ),
        CountryDetails(
            "fk",
            "+500",
            "Falkland Islands (Malvinas)",
            Res.drawable.flag_fk
        ),
        CountryDetails(
            "fm",
            "+691",
            "Micronesia, Federated States Of",
            Res.drawable.flag_fm
        ),
        CountryDetails(
            "fo",
            "+298",
            "Faroe Islands",
            Res.drawable.flag_fo
        ),
        CountryDetails(
            "fr",
            "+33",
            "France",
            Res.drawable.flag_fr
        ),
        CountryDetails(
            "ga",
            "+241",
            "Gabon",
            Res.drawable.flag_ga
        ),
        CountryDetails(
            "gb",
            "+44",
            "United Kingdom",
            Res.drawable.flag_gb
        ),
        CountryDetails(
            "gd",
            "+1",
            "Grenada",
            Res.drawable.flag_gd
        ),
        CountryDetails(
            "ge",
            "+995",
            "Georgia",
            Res.drawable.flag_ge
        ),
        CountryDetails(
            "gf",
            "+594",
            "French Guyana",
            Res.drawable.flag_gf
        ),
        CountryDetails(
            "gh",
            "+233",
            "Ghana",
            Res.drawable.flag_gh
        ),
        CountryDetails(
            "gi",
            "+350",
            "Gibraltar",
            Res.drawable.flag_gi
        ),
        CountryDetails(
            "gl",
            "+299",
            "Greenland",
            Res.drawable.flag_gl
        ),
        CountryDetails(
            "gm",
            "+220",
            "Gambia",
            Res.drawable.flag_gm
        ),
        CountryDetails(
            "gn",
            "+224",
            "Guinea",
            Res.drawable.flag_gn
        ),
        CountryDetails(
            "gp",
            "+450",
            "Guadeloupe",
            Res.drawable.flag_gp
        ),
        CountryDetails(
            "gq",
            "+240",
            "Equatorial Guinea",
            Res.drawable.flag_gq
        ),
        CountryDetails(
            "gr",
            "+30",
            "Greece",
            Res.drawable.flag_gr
        ),
        CountryDetails(
            "gt",
            "+502",
            "Guatemala",
            Res.drawable.flag_gt
        ),
        CountryDetails(
            "gu",
            "+1",
            "Guam",
            Res.drawable.flag_gu
        ),
        CountryDetails(
            "gw",
            "+245",
            "Guinea-Bissau",
            Res.drawable.flag_gw
        ),
        CountryDetails(
            "gy",
            "+592",
            "Guyana",
            Res.drawable.flag_gy
        ),
        CountryDetails(
            "hk",
            "+852",
            "Hong Kong",
            Res.drawable.flag_hk
        ),
        CountryDetails(
            "hn",
            "+504",
            "Honduras",
            Res.drawable.flag_hn
        ),
        CountryDetails(
            "hr",
            "+385",
            "Croatia",
            Res.drawable.flag_hr
        ),
        CountryDetails(
            "ht",
            "+509",
            "Haiti",
            Res.drawable.flag_ht
        ),
        CountryDetails(
            "hu",
            "+36",
            "Hungary",
            Res.drawable.flag_hu
        ),
        CountryDetails(
            "id",
            "+62",
            "Indonesia",
            Res.drawable.flag_id
        ),
        CountryDetails(
            "ie",
            "+353",
            "Ireland",
            Res.drawable.flag_ie
        ),
        CountryDetails(
            "il",
            "+972",
            "Israel",
            Res.drawable.flag_il
        ),
        CountryDetails(
            "im",
            "+44",
            "Isle Of Man",
            Res.drawable.flag_im
        ),
        CountryDetails(
            "is",
            "+354",
            "Iceland",
            Res.drawable.flag_iceland
        ),
        CountryDetails(
            "in",
            "+91",
            "India",
            Res.drawable.flag_india
        ),
        CountryDetails(
            "io",
            "+246",
            "British Indian Ocean Territory",
            Res.drawable.flag_io
        ),
        CountryDetails(
            "iq",
            "+964",
            "Iraq",
            Res.drawable.flag_iq
        ),
        CountryDetails(
            "ir",
            "+98",
            "Iran, Islamic Republic Of",
            Res.drawable.flag_ir
        ),
        CountryDetails(
            "it",
            "+39",
            "Italy",
            Res.drawable.flag_it
        ),
        CountryDetails(
            "je",
            "+44",
            "Jersey",
            Res.drawable.flag_je
        ),
        CountryDetails(
            "jm",
            "+1",
            "Jamaica",
            Res.drawable.flag_jm
        ),
        CountryDetails(
            "jo",
            "+962",
            "Jordan",
            Res.drawable.flag_jo
        ),
        CountryDetails(
            "jp",
            "+81",
            "Japan",
            Res.drawable.flag_jp
        ),
        CountryDetails(
            "ke",
            "+254",
            "Kenya",
            Res.drawable.flag_ke
        ),
        CountryDetails(
            "kg",
            "+996",
            "Kyrgyzstan",
            Res.drawable.flag_kg
        ),
        CountryDetails(
            "kh",
            "+855",
            "Cambodia",
            Res.drawable.flag_kh
        ),
        CountryDetails(
            "ki",
            "+686",
            "Kiribati",
            Res.drawable.flag_ki
        ),
        CountryDetails(
            "km",
            "+269",
            "Comoros",
            Res.drawable.flag_km
        ),
        CountryDetails(
            "kn",
            "+1",
            "Saint Kitts and Nevis",
            Res.drawable.flag_kn
        ),
        CountryDetails(
            "kp",
            "+850",
            "North Korea",
            Res.drawable.flag_kp
        ),
        CountryDetails(
            "kr",
            "+82",
            "South Korea",
            Res.drawable.flag_kr
        ),
        CountryDetails(
            "kw",
            "+965",
            "Kuwait",
            Res.drawable.flag_kw
        ),
        CountryDetails(
            "ky",
            "+1",
            "Cayman Islands",
            Res.drawable.flag_ky
        ),
        CountryDetails(
            "kz",
            "+7",
            "Kazakhstan",
            Res.drawable.flag_kz
        ),
        CountryDetails(
            "la",
            "+856",
            "Lao People's Democratic Republic",
            Res.drawable.flag_la
        ),
        CountryDetails(
            "lb",
            "+961",
            "Lebanon",
            Res.drawable.flag_lb
        ),
        CountryDetails(
            "lc",
            "+1",
            "Saint Lucia",
            Res.drawable.flag_lc
        ),
        CountryDetails(
            "li",
            "+423",
            "Liechtenstein",
            Res.drawable.flag_li
        ),
        CountryDetails(
            "lk",
            "+94",
            "Sri Lanka",
            Res.drawable.flag_lk
        ),
        CountryDetails(
            "lr",
            "+231",
            "Liberia",
            Res.drawable.flag_lr
        ),
        CountryDetails(
            "ls",
            "+266",
            "Lesotho",
            Res.drawable.flag_ls
        ),
        CountryDetails(
            "lt",
            "+370",
            "Lithuania",
            Res.drawable.flag_lt
        ),
        CountryDetails(
            "lu",
            "+352",
            "Luxembourg",
            Res.drawable.flag_lu
        ),
        CountryDetails(
            "lv",
            "+371",
            "Latvia",
            Res.drawable.flag_lv
        ),
        CountryDetails(
            "ly",
            "+218",
            "Libya",
            Res.drawable.flag_ly
        ),
        CountryDetails(
            "ma",
            "+212",
            "Morocco",
            Res.drawable.flag_ma
        ),
        CountryDetails(
            "mc",
            "+377",
            "Monaco",
            Res.drawable.flag_mc
        ),
        CountryDetails(
            "md",
            "+373",
            "Moldova, Republic Of",
            Res.drawable.flag_md
        ),
        CountryDetails(
            "me",
            "+382",
            "Montenegro",
            Res.drawable.flag_me
        ),
        CountryDetails(
            "mf",
            "+590",
            "Saint Martin",
            Res.drawable.flag_mf
        ),
        CountryDetails(
            "mg",
            "+261",
            "Madagascar",
            Res.drawable.flag_mg
        ),
        CountryDetails(
            "mh",
            "+692",
            "Marshall Islands",
            Res.drawable.flag_mh
        ),
        CountryDetails(
            "mk",
            "+389",
            "Macedonia (FYROM)",
            Res.drawable.flag_mk
        ),
        CountryDetails(
            "ml",
            "+223",
            "Mali",
            Res.drawable.flag_ml
        ),
        CountryDetails(
            "mm",
            "+95",
            "Myanmar",
            Res.drawable.flag_mm
        ),
        CountryDetails(
            "mn",
            "+976",
            "Mongolia",
            Res.drawable.flag_mn
        ),
        CountryDetails(
            "mo",
            "+853",
            "Macau",
            Res.drawable.flag_mo
        ),
        CountryDetails(
            "mp",
            "+1",
            "Northern Mariana Islands",
            Res.drawable.flag_mp
        ),
        CountryDetails(
            "mq",
            "+596",
            "Martinique",
            Res.drawable.flag_mq
        ),
        CountryDetails(
            "mr",
            "+222",
            "Mauritania",
            Res.drawable.flag_mr
        ),
        CountryDetails(
            "ms",
            "+1",
            "Montserrat",
            Res.drawable.flag_ms
        ),
        CountryDetails(
            "mt",
            "+356",
            "Malta",
            Res.drawable.flag_mt
        ),
        CountryDetails(
            "mu",
            "+230",
            "Mauritius",
            Res.drawable.flag_mu
        ),
        CountryDetails(
            "mv",
            "+960",
            "Maldives",
            Res.drawable.flag_mv
        ),
        CountryDetails(
            "mw",
            "+265",
            "Malawi",
            Res.drawable.flag_mw
        ),
        CountryDetails(
            "mx",
            "+52",
            "Mexico",
            Res.drawable.flag_mx
        ),
        CountryDetails(
            "my",
            "+60",
            "Malaysia",
            Res.drawable.flag_my
        ),
        CountryDetails(
            "mz",
            "+258",
            "Mozambique",
            Res.drawable.flag_mz
        ),
        CountryDetails(
            "na",
            "+264",
            "Namibia",
            Res.drawable.flag_na
        ),
        CountryDetails(
            "nc",
            "+687",
            "New Caledonia",
            Res.drawable.flag_nc
        ),
        CountryDetails(
            "ne",
            "+227",
            "Niger",
            Res.drawable.flag_ne
        ),
        CountryDetails(
            "nf",
            "+672",
            "Norfolk Islands",
            Res.drawable.flag_nf
        ),
        CountryDetails(
            "ng",
            "+234",
            "Nigeria",
            Res.drawable.flag_ng
        ),
        CountryDetails(
            "ni",
            "+505",
            "Nicaragua",
            Res.drawable.flag_ni
        ),
        CountryDetails(
            "nl",
            "+31",
            "Netherlands",
            Res.drawable.flag_nl
        ),
        CountryDetails(
            "no",
            "+47",
            "Norway",
            Res.drawable.flag_no
        ),
        CountryDetails(
            "np",
            "+977",
            "Nepal",
            Res.drawable.flag_np
        ),
        CountryDetails(
            "nr",
            "+674",
            "Nauru",
            Res.drawable.flag_nr
        ),
        CountryDetails(
            "nu",
            "+683",
            "Niue",
            Res.drawable.flag_nu
        ),
        CountryDetails(
            "nz",
            "+64",
            "New Zealand",
            Res.drawable.flag_nz
        ),
        CountryDetails(
            "om",
            "+968",
            "Oman",
            Res.drawable.flag_om
        ),
        CountryDetails(
            "pa",
            "+507",
            "Panama",
            Res.drawable.flag_pa
        ),
        CountryDetails(
            "pe",
            "+51",
            "Peru",
            Res.drawable.flag_pe
        ),
        CountryDetails(
            "pf",
            "+689",
            "French Polynesia",
            Res.drawable.flag_pf
        ),
        CountryDetails(
            "pg",
            "+675",
            "Papua New Guinea",
            Res.drawable.flag_pg
        ),
        CountryDetails(
            "ph",
            "+63",
            "Philippines",
            Res.drawable.flag_ph
        ),
        CountryDetails(
            "pk",
            "+92",
            "Pakistan",
            Res.drawable.flag_pk
        ),
        CountryDetails(
            "pl",
            "+48",
            "Poland",
            Res.drawable.flag_pl
        ),
        CountryDetails(
            "pm",
            "+508",
            "Saint Pierre And Miquelon",
            Res.drawable.flag_pm
        ),
        CountryDetails(
            "pn",
            "+870",
            "Pitcairn Islands",
            Res.drawable.flag_pn
        ),
        CountryDetails(
            "pr",
            "+1",
            "Puerto Rico",
            Res.drawable.flag_pr
        ),
        CountryDetails(
            "ps",
            "+970",
            "Palestine",
            Res.drawable.flag_ps
        ),
        CountryDetails(
            "pt",
            "+351",
            "Portugal",
            Res.drawable.flag_pt
        ),
        CountryDetails(
            "pw",
            "+680",
            "Palau",
            Res.drawable.flag_pw
        ),
        CountryDetails(
            "py",
            "+595",
            "Paraguay",
            Res.drawable.flag_py
        ),
        CountryDetails(
            "qa",
            "+974",
            "Qatar",
            Res.drawable.flag_qa
        ),
        CountryDetails(
            "re",
            "+262",
            "Réunion",
            Res.drawable.flag_re
        ),
        CountryDetails(
            "ro",
            "+40",
            "Romania",
            Res.drawable.flag_ro
        ),
        CountryDetails(
            "rs",
            "+381",
            "Serbia",
            Res.drawable.flag_rs
        ),
        CountryDetails(
            "ru",
            "+7",
            "Russian Federation",
            Res.drawable.flag_ru
        ),
        CountryDetails(
            "rw",
            "+250",
            "Rwanda",
            Res.drawable.flag_rw
        ),
        CountryDetails(
            "sa",
            "+966",
            "Saudi Arabia",
            Res.drawable.flag_sa
        ),
        CountryDetails(
            "sb",
            "+677",
            "Solomon Islands",
            Res.drawable.flag_sb
        ),
        CountryDetails(
            "sc",
            "+248",
            "Seychelles",
            Res.drawable.flag_sc
        ),
        CountryDetails(
            "sd",
            "+249",
            "Sudan",
            Res.drawable.flag_sd
        ),
        CountryDetails(
            "se",
            "+46",
            "Sweden",
            Res.drawable.flag_se
        ),
        CountryDetails(
            "sg",
            "+65",
            "Singapore",
            Res.drawable.flag_sg
        ),
        CountryDetails(
            "sh",
            "+290",
            "Saint Helena, Ascension And Tristan Da Cunha",
            Res.drawable.flag_sh
        ),
        CountryDetails(
            "si",
            "+386",
            "Slovenia",
            Res.drawable.flag_si
        ),
        CountryDetails(
            "sk",
            "+421",
            "Slovakia",
            Res.drawable.flag_sk
        ),
        CountryDetails(
            "sl",
            "+232",
            "Sierra Leone",
            Res.drawable.flag_sl
        ),
        CountryDetails(
            "sm",
            "+378",
            "San Marino",
            Res.drawable.flag_sm
        ),
        CountryDetails(
            "sn",
            "+221",
            "Senegal",
            Res.drawable.flag_sn
        ),
        CountryDetails(
            "so",
            "+252",
            "Somalia",
            Res.drawable.flag_so
        ),
        CountryDetails(
            "sr",
            "+597",
            "Suriname",
            Res.drawable.flag_sr
        ),
        CountryDetails(
            "ss",
            "+211",
            "South Sudan",
            Res.drawable.flag_ss
        ),
        CountryDetails(
            "st",
            "+239",
            "Sao Tome And Principe",
            Res.drawable.flag_st
        ),
        CountryDetails(
            "sv",
            "+503",
            "El Salvador",
            Res.drawable.flag_sv
        ),
        CountryDetails(
            "sx",
            "+1",
            "Sint Maarten",
            Res.drawable.flag_sx
        ),
        CountryDetails(
            "sy",
            "+963",
            "Syrian Arab Republic",
            Res.drawable.flag_sy
        ),
        CountryDetails(
            "sz",
            "+268",
            "Swaziland",
            Res.drawable.flag_sz
        ),
        CountryDetails(
            "tc",
            "+1",
            "Turks and Caicos Islands",
            Res.drawable.flag_tc
        ),
        CountryDetails(
            "td",
            "+235",
            "Chad",
            Res.drawable.flag_td
        ),
        CountryDetails(
            "tg",
            "+228",
            "Togo",
            Res.drawable.flag_tg
        ),
        CountryDetails(
            "th",
            "+66",
            "Thailand",
            Res.drawable.flag_th
        ),
        CountryDetails(
            "tj",
            "+992",
            "Tajikistan",
            Res.drawable.flag_tj
        ),
        CountryDetails(
            "tk",
            "+690",
            "Tokelau",
            Res.drawable.flag_tk
        ),
        CountryDetails(
            "tl",
            "+670",
            "Timor-leste",
            Res.drawable.flag_tl
        ),
        CountryDetails(
            "tm",
            "+993",
            "Turkmenistan",
            Res.drawable.flag_tm
        ),
        CountryDetails(
            "tn",
            "+216",
            "Tunisia",
            Res.drawable.flag_tn
        ),
        CountryDetails(
            "to",
            "+676",
            "Tonga",
            Res.drawable.flag_to
        ),
        CountryDetails(
            "tr",
            "+90",
            "Turkey",
            Res.drawable.flag_tr
        ),
        CountryDetails(
            "tt",
            "+1",
            "Trinidad &amp; Tobago",
            Res.drawable.flag_tt
        ),
        CountryDetails(
            "tv",
            "+688",
            "Tuvalu",
            Res.drawable.flag_tv
        ),
        CountryDetails(
            "tw",
            "+886",
            "Taiwan",
            Res.drawable.flag_tw
        ),
        CountryDetails(
            "tz",
            "+255",
            "Tanzania, United Republic Of",
            Res.drawable.flag_tz
        ),
        CountryDetails(
            "ua",
            "+380",
            "Ukraine",
            Res.drawable.flag_ua
        ),
        CountryDetails(
            "ug",
            "+256",
            "Uganda",
            Res.drawable.flag_ug
        ),
        CountryDetails(
            "us",
            "+1",
            "United States",
            Res.drawable.flag_us
        ),
        CountryDetails(
            "uy",
            "+598",
            "Uruguay",
            Res.drawable.flag_uy
        ),
        CountryDetails(
            "uz",
            "+998",
            "Uzbekistan",
            Res.drawable.flag_uz
        ),
        CountryDetails(
            "va",
            "+379",
            "Holy See (vatican City State)",
            Res.drawable.flag_va
        ),
        CountryDetails(
            "vc",
            "+1",
            "Saint Vincent &amp; The Grenadines",
            Res.drawable.flag_vc
        ),
        CountryDetails(
            "ve",
            "+58",
            "Venezuela, Bolivarian Republic Of",
            Res.drawable.flag_ve
        ),
        CountryDetails(
            "vg",
            "+1",
            "British Virgin Islands",
            Res.drawable.flag_vg
        ),
        CountryDetails(
            "vi",
            "+1",
            "US Virgin Islands",
            Res.drawable.flag_vi
        ),
        CountryDetails(
            "vn",
            "+84",
            "Vietnam",
            Res.drawable.flag_vn
        ),
        CountryDetails(
            "vu",
            "+678",
            "Vanuatu",
            Res.drawable.flag_vu
        ),
        CountryDetails(
            "wf",
            "+681",
            "Wallis And Futuna",
            Res.drawable.flag_wf
        ),
        CountryDetails(
            "ws",
            "4685",
            "Samoa",
            Res.drawable.flag_ws
        ),
        CountryDetails(
            "xk",
            "+383",
            "Kosovo",
            Res.drawable.flag_xk
        ),
        CountryDetails(
            "ye",
            "+967",
            "Yemen",
            Res.drawable.flag_ye
        ),
        CountryDetails(
            "yt",
            "+262",
            "Mayotte",
            Res.drawable.flag_yt
        ),
        CountryDetails(
            "za",
            "+27",
            "South Africa",
            Res.drawable.flag_za
        ),
        CountryDetails(
            "zm",
            "+260",
            "Zambia",
            Res.drawable.flag_zm
        ),
        CountryDetails(
            "zw",
            "+263",
            "Zimbabwe",
            Res.drawable.flag_zw
        ),
    ).sortedBy { it.countryName }

    fun Modifier.noRippleClickable(
        onClick: () -> Unit
    ) = composed {
        this.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick()
        }
    }
}