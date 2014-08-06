package com.jps.quranic.arabic.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jps.quranic.arabic.R;
import com.jps.quranic.arabic.adapter.HomeArrayAdapter;
import com.jps.quranic.arabic.util.Lesson;
import com.jps.quranic.arabic.util.Util;

/**
 * User: shah
 * Date: 2/25/12
 * Time: 5:57 PM
 */
public class HomeActivity extends ListActivity
{
  private final int[] _lesson1 = { R.array.hooa, R.array.hum, R.array.anta, R.array.antum, R.array.ana, R.array.nahnu,
                                   R.array.heeya };
  private final int[] _lesson2 = { R.array.rabbuhu, R.array.rabbuhum, R.array.rabbuka, R.array.rabbukum, R.array.rabbi,
                                   R.array.rabbuna, R.array.rabbuha };
  private final int[] _lesson3 = { R.array.lahu, R.array.lahum, R.array.laka, R.array.lakum, R.array.lee, R.array.lana,
                                   R.array.laha };
  private final int[] _lesson4 = { R.array.fa_ala, R.array.fa_alu, R.array.fa_alta, R.array.fa_altum, R.array.fa_altu,
                                   R.array.fa_alna };
  private final int[] _lesson5 = { R.array.min_hu, R.array.min_hum, R.array.min_ka, R.array.min_kum, R.array.min_ni,
                                   R.array.min_na, R.array.min_ha };
  private final int[] _lesson6 = { R.array.an_hu, R.array.an_hum, R.array.an_ka, R.array.an_kum, R.array.an_ni,
                                   R.array.an_na, R.array.an_ha };
  private final int[] _lesson7 = { R.array.yaf_alu, R.array.yaf_alu_na, R.array.taf_alu, R.array.taf_alu_na,
                                   R.array.af_alu, R.array.naf_alu };
  private final int[] _lesson8 = { R.array.ma_ahu, R.array.ma_ahum, R.array.ma_aka, R.array.ma_akum, R.array.ma_ee,
                                   R.array.ma_ee_na, R.array.ma_aha };
  private final int[] _lesson9 = { R.array.fee_hee, R.array.fee_him, R.array.fee_ka, R.array.fee_kum, R.array.fee_ya,
                                   R.array.fee_na, R.array.fee_ha };
  private final int[] _lesson10 = { R.array.if_al, R.array.if_a_lu, R.array.la_taf_al, R.array.la_taf_a_lu };
  private final int[] _lesson11 = { R.array.bi_hi, R.array.bi_him, R.array.bi_ka, R.array.bi_kum, R.array.bi,
                                    R.array.bi_na, R.array.bi_ha };
  private final int[] _lesson12 = { R.array.a_lai_hi, R.array.a_lai_him, R.array.a_lai_ka, R.array.a_lai_kum,
                                    R.array.a_lai_ya, R.array.a_lai_na, R.array.a_lai_ha };
  private final int[] _lesson13 = { R.array.fa_il, R.array.maf_ul, R.array.fail, R.array.fa_i_lun, R.array.fa_i_lin,
                                    R.array.maf_u_lun, R.array.maf_u_lin };
  private final int[] _lesson14 = { R.array.ilai_hi, R.array.ilai_him, R.array.ilai_ka, R.array.ilai_kum,
                                    R.array.ilai_ya, R.array.ilai_na, R.array.ilai_ha };
  private final int[] _lesson15 = { R.array.fa_a_lat, R.array.hiya_taf_a_lu, R.array.fa_ila, R.array.fa_ilat,
                                    R.array.maf_ula, R.array.maf_ulat };
  private final int[] _lesson16 = { R.array.fa_ta_ha, R.array.fa_ta_hu, R.array.fa_tah_ta, R.array.fa_tah_tum,
                                    R.array.fa_tah_tu, R.array.fa_tah_na, R.array.yaf_ta_hu, R.array.yaf_ta_hu_na,
                                    R.array.taf_ta_hu, R.array.taf_ta_hu_na, R.array.af_ta_hu, R.array.naf_ta_hu,
                                    R.array.if_tah, R.array.if_ta_hu, R.array.la_taf_tah, R.array.la_taf_ta_hu,
                                    R.array.fa_tih, R.array.maf_tuh, R.array.fath, R.array.fa_ta_hat,
                                    R.array.hiya_taf_ta_hu };
  private final int[] _lesson17 = { R.array.inda_hu, R.array.inda_hum, R.array.inda_ka, R.array.inda_kum, R.array.in_di,
                                    R.array.inda_na, R.array.inda_ha };
  private final int[] _lesson18 = { R.array.ja_ala, R.array.ja_alu, R.array.ja_al_ta, R.array.ja_al_tum,
                                    R.array.ja_al_tu, R.array.ja_al_na, R.array.yaj_alu, R.array.yaj_alu_na,
                                    R.array.taj_alu, R.array.taj_alu_na, R.array.aj_alu, R.array.naj_alu, R.array.ij_al,
                                    R.array.ij_alu, R.array.la_taj_al, R.array.la_taj_alu, R.array.ja_il,
                                    R.array.maj_ul, R.array.jal, R.array.ja_a_lat, R.array.hiya_taj_alu };
  private final int[] _lesson19 = { R.array.na_sa_ra, R.array.na_sa_ru, R.array.na_sar_ta, R.array.na_sar_tum,
                                    R.array.na_sar_tu, R.array.na_sar_na, R.array.yan_su_ru, R.array.yan_su_ru_na,
                                    R.array.tan_su_ru, R.array.tan_su_ru_na, R.array.an_su_ru, R.array.nan_su_ru,
                                    R.array.un_sur, R.array.un_su_ru, R.array.la_tan_sur, R.array.la_tan_su_ru,
                                    R.array.na_sir, R.array.man_sur, R.array.nasr, R.array.na_sa_rat,
                                    R.array.hiya_tan_su_ru };
  private final int[] _lesson20 = { R.array.a_ba_da, R.array.a_ba_du, R.array.a_bat_ta, R.array.a_bat_tum,
                                    R.array.a_bat_tu, R.array.a_bad_na, R.array.ya_bu_du, R.array.ya_bu_du_na,
                                    R.array.ta_bu_du, R.array.ta_bu_du_na, R.array.a_bu_du, R.array.na_bu_du,
                                    R.array.o_bud, R.array.o_bu_du, R.array.la_ta_bud, R.array.la_ta_bu_du,
                                    R.array.a_bid, R.array.ma_bud, R.array.i_ba_da, R.array.a_ba_dat,
                                    R.array.hiya_ta_bu_du };
  private final int[] _lesson21 = { R.array.za_ka_ra, R.array.za_ka_ru, R.array.za_kar_ta, R.array.za_kar_tum,
                                    R.array.za_kar_tu, R.array.za_kar_na, R.array.yaz_ku_ru, R.array.yaz_ku_ru_na,
                                    R.array.taz_ku_ru, R.array.taz_ku_ru_na, R.array.az_ku_ru, R.array.naz_ku_ru,
                                    R.array.uz_kur, R.array.uz_ku_ru, R.array.la_taz_kur, R.array.la_taz_ku_ru,
                                    R.array.za_kir, R.array.maz_kur, R.array.zikr, R.array.za_ka_rat,
                                    R.array.hiya_taz_ku_ru };
  private final int[] _lesson22 = { R.array.ha_za, R.array.ha_u_la_i, R.array.za_li_ka, R.array.u_la_i_ka,
                                    R.array.al_la_zi, R.array.al_la_zi_na, R.array.ha_zi_hi, R.array.til_ka,
                                    R.array.al_la_ti, R.array.al_laa_ti };
  private final int[] _lesson23 = { R.array.ka_fa_ra, R.array.ka_fa_ru, R.array.ka_far_ta, R.array.ka_far_tum,
                                    R.array.ka_far_tu, R.array.ka_far_na, R.array.yak_fu_ru, R.array.yak_fu_ru_na,
                                    R.array.tak_fu_ru, R.array.tak_fu_ru_na, R.array.ak_fu_ru, R.array.nak_fu_ru,
                                    R.array.uk_fur, R.array.uk_fu_ru, R.array.la_tak_fur, R.array.la_tak_fu_ru,
                                    R.array.ka_fir, R.array.mak_fur, R.array.kufr, R.array.ka_fa_rat,
                                    R.array.hiya_tak_fur };
  private final int[] _lesson24 = { R.array.da_ra_ba, R.array.da_ra_bu, R.array.da_rab_ta, R.array.da_rab_tum,
                                    R.array.da_rab_tu, R.array.da_rab_na, R.array.yad_ri_bu, R.array.yad_ri_bu_na,
                                    R.array.tad_ri_bu, R.array.tad_ri_bu_na, R.array.ad_ri_bu, R.array.nad_ri_bu,
                                    R.array.id_rib, R.array.id_ri_bu, R.array.la_tad_rib, R.array.la_tad_ri_bu,
                                    R.array.da_rib, R.array.mad_rub, R.array.darb, R.array.da_ra_bat,
                                    R.array.hiya_tad_ri_bu };
  private final int[] _lesson25 = { R.array.za_la_ma, R.array.za_la_mu, R.array.za_lam_ta, R.array.za_lam_tum,
                                    R.array.za_lam_tu, R.array.za_lam_na, R.array.yaz_li_mu, R.array.yaz_li_mu_na,
                                    R.array.taz_li_mu, R.array.taz_li_mu_na, R.array.az_li_mu, R.array.naz_li_mu,
                                    R.array.iz_lim, R.array.iz_li_mu, R.array.la_taz_lim, R.array.la_taz_li_mu,
                                    R.array.za_lim, R.array.maz_lum, R.array.zulm, R.array.za_la_mat,
                                    R.array.hiya_taz_li_mu };
  private final int[] _lesson26 = { R.array.sa_mi_a, R.array.sa_mi_u, R.array.sa_me_ta, R.array.sa_me_tum,
                                    R.array.sa_me_tu, R.array.sa_me_na, R.array.yas_ma_u, R.array.yas_ma_u_na,
                                    R.array.tas_ma_u, R.array.tas_ma_u_na, R.array.as_ma_u, R.array.nas_ma_u,
                                    R.array.is_ma, R.array.is_ma_u, R.array.la_tas_ma, R.array.la_tas_ma_u,
                                    R.array.sa_me, R.array.mas_mu, R.array.sam_a, R.array.sa_mi_at,
                                    R.array.hiya_tas_ma_u };
  private final int[] _lesson27 = { R.array.a_li_ma, R.array.a_li_mu, R.array.a_lim_ta, R.array.a_lim_tum,
                                    R.array.a_lim_tu, R.array.a_lim_na, R.array.ya_la_mu, R.array.ya_la_mu_na,
                                    R.array.ta_la_mu, R.array.ta_la_mu_na, R.array.a_la_mu, R.array.na_la_mu,
                                    R.array.e_lam, R.array.e_la_mu, R.array.la_ta_lam, R.array.la_ta_la_mu,
                                    R.array.a_lim, R.array.ma_lum, R.array.ilm, R.array.a_li_mat,
                                    R.array.hiya_ta_la_mu };
  private final int[] _lesson28 = { R.array.a_mi_la, R.array.a_mi_lu, R.array.a_mil_ta, R.array.a_mil_tum,
                                    R.array.a_mil_tu, R.array.a_mil_na, R.array.ya_ma_lu, R.array.ya_ma_lu_na,
                                    R.array.ta_ma_lu, R.array.ta_ma_lu_na, R.array.a_ma_lu, R.array.na_ma_lu,
                                    R.array.e_mal, R.array.e_ma_lu, R.array.la_ta_mal, R.array.la_ta_ma_lu,
                                    R.array.a_mil, R.array.ma_mul, R.array.a_mal, R.array.a_mi_lat,
                                    R.array.hiya_ta_ma_lu };
  private final int[] _lesson29 = { R.array.dal_la, R.array.dal_lu, R.array.da_lal_ta, R.array.da_lal_tum,
                                    R.array.da_lal_tu, R.array.da_lal_na, R.array.ya_dil_lu, R.array.ya_dil_lu_na,
                                    R.array.ta_dil_lu, R.array.ta_dil_lu_na, R.array.a_dil_lu, R.array.na_dil_lu,
                                    R.array.dil_la, R.array.dil_lu, R.array.la_ta_dil_la, R.array.la_ta_dil_lu,
                                    R.array.dal, R.array.da_la_lah, R.array.dal_lat, R.array.hiya_ta_dil_lu };
  private final int[] _lesson30 = { R.array.zan_na, R.array.zan_nu, R.array.za_nan_ta, R.array.za_nan_tum,
                                    R.array.za_nan_tu, R.array.za_nan_na, R.array.ya_zun_nu, R.array.ya_zun_nu_na,
                                    R.array.ta_zun_nu, R.array.ta_zun_nu_na, R.array.a_zun_nu, R.array.na_zun_nu,
                                    R.array.zun_na, R.array.zun_nu, R.array.la_ta_zun_na, R.array.la_ta_zun_nu,
                                    R.array.zaan, R.array.zan, R.array.zan_nat, R.array.hiya_ta_zun_nu };
  private final int[] _lesson31 = { R.array.wa_a_da, R.array.wa_a_du, R.array.wa_at_ta, R.array.wa_at_tum,
                                    R.array.wa_at_tu, R.array.wa_ad_na, R.array.ya_i_du, R.array.ya_i_du_na,
                                    R.array.ta_i_du, R.array.ta_i_du_na, R.array.a_i_du, R.array.na_i_du,
                                    R.array.id, R.array.i_du, R.array.la_ta_id, R.array.la_ta_i_du,
                                    R.array.wa_id, R.array.mau_ud, R.array.waad, R.array.wa_a_dat,
                                    R.array.hiya_ta_i_du };
  private final int[] _lesson32 = { R.array.wa_ja_da, R.array.wa_ja_du, R.array.wa_jat_ta, R.array.wa_jat_tum,
                                    R.array.wa_jat_tu, R.array.wa_jad_na, R.array.ya_ji_du, R.array.ya_ji_du_na,
                                    R.array.ta_ji_du, R.array.ta_ji_du_na, R.array.a_ji_du, R.array.na_ji_du,
                                    R.array.jid, R.array.ji_du, R.array.la_ta_jid, R.array.la_ta_ji_du,
                                    R.array.wa_jid, R.array.mau_jud, R.array.wu_jud, R.array.wa_ja_dat,
                                    R.array.hiya_ta_ji_du };
  private final int[] _lesson33 = { R.array.qa_la, R.array.qa_lu, R.array.qul_ta, R.array.qul_tum,
                                    R.array.qul_tu, R.array.qul_na, R.array.ya_qu_lu, R.array.ya_qu_lu_na,
                                    R.array.ta_qu_lu, R.array.ta_qu_lu_na, R.array.a_qu_lu, R.array.na_qu_lu,
                                    R.array.qul, R.array.qu_lu, R.array.la_ta_qul, R.array.la_ta_qu_lu,
                                    R.array.qa_il, R.array.ma_qul, R.array.qaul, R.array.qa_lat,
                                    R.array.hiya_ta_qu_lu };
  private final int[] _lesson34 = { R.array.ka_na, R.array.ka_nu, R.array.kun_ta, R.array.kun_tum,
                                    R.array.kun_tu, R.array.kun_na, R.array.ya_ku_nu, R.array.ya_ku_nu_na,
                                    R.array.ta_ku_nu, R.array.ta_ku_nu_na, R.array.a_ku_nu, R.array.na_ku_nu,
                                    R.array.kun, R.array.ku_nu, R.array.la_ta_kun, R.array.la_ta_ku_nu,
                                    R.array.ka_in, R.array.kaun, R.array.ka_nat, R.array.hiya_ta_ku_nu };
  private final int[] _lesson35 = { R.array.za_da, R.array.za_du, R.array.zat_ta, R.array.zat_tum,
                                    R.array.zat_tu, R.array.zad_na, R.array.ya_zi_du, R.array.ya_zi_du_na,
                                    R.array.ta_zi_du, R.array.ta_zi_du_na, R.array.a_zi_du, R.array.na_zi_du,
                                    R.array.zid, R.array.zi_du, R.array.la_ta_zid, R.array.la_ta_zi_du,
                                    R.array.za_id, R.array.ma_zid, R.array.zi_ya_dah, R.array.za_dat,
                                    R.array.hiya_ta_zi_du };
  private final int[] _lesson36 = { R.array.da_a, R.array.da_au, R.array.da_au_ta, R.array.da_au_tum,
                                    R.array.da_au_tu, R.array.da_au_na, R.array.yad_u, R.array.yad_u_na,
                                    R.array.tad_u, R.array.tad_u_na, R.array.ad_u, R.array.nad_u,
                                    R.array.ud_u, R.array.ud_uu, R.array.la_tad_u, R.array.la_tad_uu,
                                    R.array.da_in, R.array.mad_u, R.array.du_a, R.array.da_at,
                                    R.array.hiya_tad_u };
  private final int[] _lesson37 = { R.array.ha_daa, R.array.ha_dau, R.array.ha_dai_ta, R.array.ha_dai_tum,
                                    R.array.ha_dai_tu, R.array.ha_dai_na, R.array.yah_di, R.array.yah_du_na,
                                    R.array.tah_di, R.array.tah_du_na, R.array.ah_di, R.array.nah_di,
                                    R.array.ih_di, R.array.ih_du, R.array.la_tah_di, R.array.la_tah_du,
                                    R.array.ha_din, R.array.mah_di, R.array.hi_da_yah, R.array.ha_dat,
                                    R.array.hiya_tah_di };
  private final int[] _lesson38 = { R.array.kha_shi_ya, R.array.kha_shu, R.array.kha_shi_ta, R.array.kha_shi_tum,
                                    R.array.kha_shi_tu, R.array.kha_shi_na, R.array.yakh_sha, R.array.yakh_shau_na,
                                    R.array.takh_sha, R.array.takh_shau_na, R.array.akh_sha, R.array.nakh_sha,
                                    R.array.ikh_sha, R.array.ikh_shau, R.array.la_takh_sha, R.array.la_takh_shau,
                                    R.array.kha_shin, R.array.kha_shiy_ya, R.array.kha_shi_yat, R.array.hiya_takh_sha };
  private final int[] _lesson39 = { R.array.a_ma_ra, R.array.a_ma_ru, R.array.a_mar_ta, R.array.a_mar_tum,
                                    R.array.a_mar_tu, R.array.a_mar_na, R.array.ya_mu_ru, R.array.ya_mu_ru_na,
                                    R.array.ta_mu_ru, R.array.ta_mu_ru_na, R.array.a_mu_ru, R.array.na_mu_ru,
                                    R.array.mur, R.array.mu_ru, R.array.la_ta_mur, R.array.la_ta_mu_ru,
                                    R.array.a_mir, R.array.ma_mur, R.array.amr, R.array.a_ma_rat,
                                    R.array.hiya_ta_mu_ru };
  private final int[] _lesson40 = { R.array.ra_a, R.array.ra_au, R.array.ra_ai_ta, R.array.ra_ai_tum,
                                    R.array.ra_ai_tu, R.array.ra_ai_na, R.array.ya_ra, R.array.ya_rau_na,
                                    R.array.ta_ra, R.array.ta_rau_na, R.array.a_ra, R.array.na_ra,
                                    R.array.ra, R.array.rau, R.array.la_ta_ra, R.array.la_ta_rau,
                                    R.array.ra_in, R.array.mar_i, R.array.ra_yun, R.array.ra_at,
                                    R.array.hiya_ta_ra };
  private final int[] _lesson41 = { R.array.a_ta, R.array.a_tau, R.array.a_tai_ta, R.array.a_tai_tum,
                                    R.array.a_tai_tu, R.array.a_tai_na, R.array.ya_ti, R.array.ya_tu_na,
                                    R.array.ta_ti, R.array.ta_tu_na, R.array.aa_ti, R.array.na_ti,
                                    R.array.i_ti, R.array.i_tu, R.array.la_ta_ti, R.array.la_ta_tu,
                                    R.array.aa_tin, R.array.ma_ti, R.array.it_yan, R.array.a_tat,
                                    R.array.hiya_ta_ti };
  private final int[] _lesson42 = { R.array.sha_a, R.array.shaa_u, R.array.she_ta, R.array.she_tum,
                                    R.array.she_tu, R.array.she_na, R.array.ya_sha_u, R.array.ya_sha_u_na,
                                    R.array.ta_sha_u, R.array.ta_sha_u_na, R.array.a_sha_u, R.array.na_sha_u,
                                    R.array.sha, R.array.sha_u, R.array.la_ta_sha, R.array.la_ta_sha_u,
                                    R.array.sha_in, R.array.ma_shi_ah, R.array.sha_at, R.array.hiya_ta_sha_u };
  private final int[] _lesson43 = { R.array.fa_al_na, R.array.fa_al_ti, R.array.fa_al_tun_na, R.array.yaf_al_na,
                                    R.array.taf_a_li_na, R.array.taf_al_na, R.array.if_a_li, R.array.if_al_na,
                                    R.array.la_taf_a_li, R.array.la_taf_al_na };
  private final int[] _lesson44 = { R.array.fu_i_la, R.array.fu_i_lu, R.array.fu_il_ta, R.array.fu_il_tum,
                                    R.array.fu_il_tu, R.array.fu_il_na, R.array.yuf_a_lu, R.array.yuf_a_lu_na,
                                    R.array.tuf_a_lu, R.array.tuf_a_lu_na, R.array.uf_a_lu, R.array.nuf_a_lu,
                                    R.array.fu_i_lat, R.array.hiya_tuf_a_lu };
  private final int[] _lesson45 = { R.array.sab_ba_ha, R.array.sab_ba_hu, R.array.sab_bah_ta, R.array.sab_bah_tum,
                                    R.array.sab_bah_tu, R.array.sab_bah_na, R.array.yu_sab_bi_hu, R.array.yu_sab_bi_hu_na,
                                    R.array.tu_sab_bi_hu, R.array.tu_sab_bi_hu_na, R.array.u_sab_bi_hu, R.array.nu_sab_bi_hu,
                                    R.array.sab_bih, R.array.sab_bi_hu, R.array.la_tu_sab_bih, R.array.la_tu_sab_bi_hu,
                                    R.array.mu_sab_bih, R.array.mu_sab_bah, R.array.tas_bih, R.array.sab_ba_hat,
                                    R.array.hiya_tu_sab_bi_hu };
  private final int[] _lesson46 = { R.array.kaz_za_ba, R.array.kaz_za_bu, R.array.kaz_zab_ta, R.array.kaz_zab_tum,
                                    R.array.kaz_zab_tu, R.array.kaz_zab_na, R.array.yu_kaz_zi_bu, R.array.yu_kaz_zi_bu_na,
                                    R.array.tu_kaz_zi_bu, R.array.tu_kaz_zi_bu_na, R.array.u_kaz_zi_bu, R.array.nu_kaz_zi_bu,
                                    R.array.kaz_zib, R.array.kaz_zi_bu, R.array.la_tu_kaz_zib, R.array.la_tu_kaz_zi_bu,
                                    R.array.mu_kaz_zib, R.array.mu_kaz_zab, R.array.tak_zib, R.array.kaz_za_bat,
                                    R.array.hiya_tu_kaz_zi_bu };
  private final int[] _lesson47 = { R.array.naz_za_la, R.array.naz_za_lu, R.array.naz_zal_ta, R.array.naz_zal_tum,
                                    R.array.naz_zal_tu, R.array.naz_zal_na, R.array.yu_naz_zi_lu, R.array.yu_naz_zi_lu_na,
                                    R.array.tu_naz_zi_lu, R.array.tu_naz_zi_lu_na, R.array.u_naz_zi_lu, R.array.nu_naz_zi_lu,
                                    R.array.naz_zil, R.array.naz_zi_lu, R.array.la_tu_naz_zil, R.array.la_tu_naz_zi_lu,
                                    R.array.mu_naz_zil, R.array.mu_naz_zal, R.array.tan_zil, R.array.naz_za_lat,
                                    R.array.hiya_tu_naz_zi_lu };
  private final int[] _lesson48 = { R.array.jaa_ha_da, R.array.jaa_ha_du, R.array.jaa_hat_ta, R.array.jaa_hat_tum,
                                    R.array.jaa_hat_tu, R.array.jaa_had_na, R.array.yu_jaa_hi_du, R.array.yu_jaa_hi_du_na,
                                    R.array.tu_jaa_hi_du, R.array.tu_jaa_hi_du_na, R.array.oo_jaa_hi_du, R.array.nu_jaa_hi_du,
                                    R.array.jaa_hid, R.array.jaa_hi_du, R.array.la_tu_jaa_hid, R.array.la_tu_jaa_hi_du,
                                    R.array.mu_jaa_hid, R.array.mu_jaa_had, R.array.mu_jaa_hi_dah, R.array.jaa_ha_dat,
                                    R.array.hiya_tu_jaa_hi_du };
  private final int[] _lesson49 = { R.array.naa_da, R.array.naa_dau, R.array.naa_dai_ta, R.array.naa_dai_tum,
                                    R.array.naa_dai_tu, R.array.naa_dai_naa, R.array.yu_naa_di, R.array.yu_naa_du_na,
                                    R.array.tu_naa_di, R.array.tu_naa_du_na, R.array.oo_naa_di, R.array.nu_naa_di,
                                    R.array.naa_di, R.array.naa_du, R.array.la_tu_naa_di, R.array.la_tu_naa_du,
                                    R.array.mu_naa_din, R.array.mu_naa_da, R.array.mu_naa_dah, R.array.naa_dat,
                                    R.array.hiya_tu_naa_di };
  private final int[] _lesson50 = { R.array.akh_ra_ja, R.array.akh_ra_ju, R.array.akh_raj_ta, R.array.akh_raj_tum,
                                    R.array.akh_raj_tu, R.array.akh_raj_naa, R.array.yukh_ri_ju, R.array.yukh_ri_ju_na,
                                    R.array.tukh_ri_ju, R.array.tukh_ri_ju_na, R.array.ukh_ri_ju, R.array.nukh_ri_ju,
                                    R.array.akh_rij, R.array.akh_ri_ju, R.array.la_tukh_rij, R.array.la_tukh_ri_ju,
                                    R.array.mukh_rij, R.array.mukh_raj, R.array.ikh_raaj, R.array.akh_ra_jat,
                                    R.array.hiya_tukh_ri_ju };
  private final int[] _lesson51 = { R.array.as_la_ma, R.array.as_la_mu, R.array.as_lam_ta, R.array.as_lam_tum,
                                    R.array.as_lam_tu, R.array.as_lam_na, R.array.yus_li_mu, R.array.yus_li_mu_na,
                                    R.array.tus_li_mu, R.array.tus_li_mu_na, R.array.us_li_mu, R.array.nus_li_mu,
                                    R.array.as_lim, R.array.as_li_mu, R.array.la_tus_lim, R.array.la_tus_li_mu,
                                    R.array.mus_lim, R.array.mus_lam, R.array.is_lam, R.array.as_la_mat,
                                    R.array.hiya_tus_li_mu };
  private final int[] _lesson52 = { R.array.ash_ra_ka, R.array.ash_ra_ku, R.array.ash_rak_ta, R.array.ash_rak_tum,
                                    R.array.ash_rak_tu, R.array.ash_rak_na, R.array.yush_ri_ku, R.array.yush_ri_ku_na,
                                    R.array.tush_ri_ku, R.array.tush_ri_ku_na, R.array.ush_ri_ku, R.array.nush_ri_ku,
                                    R.array.ash_rik, R.array.ash_ri_ku, R.array.la_tush_rik, R.array.la_tush_ri_ku,
                                    R.array.mush_rik, R.array.mush_rak, R.array.ish_rak, R.array.ash_ra_kat,
                                    R.array.hiya_tush_ri_ku };
  private final int[] _lesson53 = { R.array.an_za_la, R.array.an_za_lu, R.array.an_zal_ta, R.array.an_zal_tum,
                                    R.array.an_zal_tu, R.array.an_zal_na, R.array.yun_zi_lu, R.array.yun_zi_lu_na,
                                    R.array.tun_zi_lu, R.array.tun_zi_lu_na, R.array.un_zi_lu, R.array.nun_zi_lu,
                                    R.array.an_zil, R.array.an_zi_loo, R.array.la_tun_zil, R.array.la_tun_zi_lu,
                                    R.array.mun_zil, R.array.mun_zal, R.array.in_zal, R.array.an_za_lat,
                                    R.array.hiya_tun_zi_lu };
  private final int[] _lesson54 = { R.array.a_dal_la, R.array.a_dal_lu, R.array.ad_lal_ta, R.array.ad_lal_tum,
                                    R.array.ad_lal_tu, R.array.ad_lal_na, R.array.yu_dil_lu, R.array.yu_dil_lu_na,
                                    R.array.tu_dil_lu, R.array.tu_dil_lu_na, R.array.u_dil_lu, R.array.nu_dil_lu,
                                    R.array.ad_lil, R.array.ad_li_lu, R.array.la_tu_dil, R.array.la_tu_dil_lu,
                                    R.array.mu_dil, R.array.mu_dal, R.array.id_lal, R.array.a_dal_lat,
                                    R.array.hiya_tu_dil_lu };
  private final int[] _lesson55 = { R.array.a_ra_da, R.array.a_ra_du, R.array.a_rat_ta, R.array.a_rat_tum,
                                    R.array.a_rat_tu, R.array.a_rad_na, R.array.yu_ri_du, R.array.yu_ri_du_na,
                                    R.array.tu_ri_du, R.array.tu_ri_du_na, R.array.oo_ri_du, R.array.nu_ri_du,
                                    R.array.a_rid, R.array.a_ri_du, R.array.la_tu_rid, R.array.la_tu_ri_du,
                                    R.array.mu_rid, R.array.mu_rad, R.array.i_ra_dah, R.array.a_ra_dat,
                                    R.array.hiya_tu_ri_du };

  private Map<String, int[]> _lessonMap;
  private ArrayList<Lesson> _lessonList;

  // extras
  public static final String EXTRA_RESOURCE_IDS = "extra_resource_ids";

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    setContentView( R.layout.home_view );

    FlashCardActivity.PREF_NAME = this.getClass().getName();

    Resources resources = getResources();
    String[] lesson1 = resources.getStringArray( R.array.lesson_1 );
    String[] lesson2 = resources.getStringArray( R.array.lesson_2 );
    String[] lesson3 = resources.getStringArray( R.array.lesson_3 );
    String[] lesson4 = resources.getStringArray( R.array.lesson_4 );
    String[] lesson5 = resources.getStringArray( R.array.lesson_5 );
    String[] lesson6 = resources.getStringArray( R.array.lesson_6 );
    String[] lesson7 = resources.getStringArray( R.array.lesson_7 );
    String[] lesson8 = resources.getStringArray( R.array.lesson_8 );
    String[] lesson9 = resources.getStringArray( R.array.lesson_9 );
    String[] lesson10 = resources.getStringArray( R.array.lesson_10 );
    String[] lesson11 = resources.getStringArray( R.array.lesson_11 );
    String[] lesson12 = resources.getStringArray( R.array.lesson_12 );
    String[] lesson13 = resources.getStringArray( R.array.lesson_13 );
    String[] lesson14 = resources.getStringArray( R.array.lesson_14 );
    String[] lesson15 = resources.getStringArray( R.array.lesson_15 );
    String[] lesson16 = resources.getStringArray( R.array.lesson_16 );
    String[] lesson17 = resources.getStringArray( R.array.lesson_17 );
    String[] lesson18 = resources.getStringArray( R.array.lesson_18 );
    String[] lesson19 = resources.getStringArray( R.array.lesson_19 );
    String[] lesson20 = resources.getStringArray( R.array.lesson_20 );
    String[] lesson21 = resources.getStringArray( R.array.lesson_21 );
    String[] lesson22 = resources.getStringArray( R.array.lesson_22 );
    String[] lesson23 = resources.getStringArray( R.array.lesson_23 );
    String[] lesson24 = resources.getStringArray( R.array.lesson_24 );
    String[] lesson25 = resources.getStringArray( R.array.lesson_25 );
    String[] lesson26 = resources.getStringArray( R.array.lesson_26 );
    String[] lesson27 = resources.getStringArray( R.array.lesson_27 );
    String[] lesson28 = resources.getStringArray( R.array.lesson_28 );
    String[] lesson29 = resources.getStringArray( R.array.lesson_29 );
    String[] lesson30 = resources.getStringArray( R.array.lesson_30 );
    String[] lesson31 = resources.getStringArray( R.array.lesson_31 );
    String[] lesson32 = resources.getStringArray( R.array.lesson_32 );
    String[] lesson33 = resources.getStringArray( R.array.lesson_33 );
    String[] lesson34 = resources.getStringArray( R.array.lesson_34 );
    String[] lesson35 = resources.getStringArray( R.array.lesson_35 );
    String[] lesson36 = resources.getStringArray( R.array.lesson_36 );
    String[] lesson37 = resources.getStringArray( R.array.lesson_37 );
    String[] lesson38 = resources.getStringArray( R.array.lesson_38 );
    String[] lesson39 = resources.getStringArray( R.array.lesson_39 );
    String[] lesson40 = resources.getStringArray( R.array.lesson_40 );
    String[] lesson41 = resources.getStringArray( R.array.lesson_41 );
    String[] lesson42 = resources.getStringArray( R.array.lesson_42 );
    String[] lesson43 = resources.getStringArray( R.array.lesson_43 );
    String[] lesson44 = resources.getStringArray( R.array.lesson_44 );
    String[] lesson45 = resources.getStringArray( R.array.lesson_45 );
    String[] lesson46 = resources.getStringArray( R.array.lesson_46 );
    String[] lesson47 = resources.getStringArray( R.array.lesson_47 );
    String[] lesson48 = resources.getStringArray( R.array.lesson_48 );
    String[] lesson49 = resources.getStringArray( R.array.lesson_49 );
    String[] lesson50 = resources.getStringArray( R.array.lesson_50 );
    String[] lesson51 = resources.getStringArray( R.array.lesson_51 );
    String[] lesson52 = resources.getStringArray( R.array.lesson_52 );
    String[] lesson53 = resources.getStringArray( R.array.lesson_53 );
    String[] lesson54 = resources.getStringArray( R.array.lesson_54 );
    String[] lesson55 = resources.getStringArray( R.array.lesson_55 );

    _lessonMap = new HashMap<String, int[]>();
    _lessonMap.put( lesson1[0], _lesson1 );
    _lessonMap.put( lesson2[0], _lesson2 );
    _lessonMap.put( lesson3[0], _lesson3 );
    _lessonMap.put( lesson4[0], _lesson4 );
    _lessonMap.put( lesson5[0], _lesson5 );
    _lessonMap.put( lesson6[0], _lesson6 );
    _lessonMap.put( lesson7[0], _lesson7 );
    _lessonMap.put( lesson8[0], _lesson8 );
    _lessonMap.put( lesson9[0], _lesson9 );
    _lessonMap.put( lesson10[0], _lesson10 );
    _lessonMap.put( lesson11[0], _lesson11 );
    _lessonMap.put( lesson12[0], _lesson12 );
    _lessonMap.put( lesson13[0], _lesson13 );
    _lessonMap.put( lesson14[0], _lesson14 );
    _lessonMap.put( lesson15[0], _lesson15 );
    _lessonMap.put( lesson16[0], _lesson16 );
    _lessonMap.put( lesson17[0], _lesson17 );
    _lessonMap.put( lesson18[0], _lesson18 );
    _lessonMap.put( lesson19[0], _lesson19 );
    _lessonMap.put( lesson20[0], _lesson20 );
    _lessonMap.put( lesson21[0], _lesson21 );
    _lessonMap.put( lesson22[0], _lesson22 );
    _lessonMap.put( lesson23[0], _lesson23 );
    _lessonMap.put( lesson24[0], _lesson24 );
    _lessonMap.put( lesson25[0], _lesson25 );
    _lessonMap.put( lesson26[0], _lesson26 );
    _lessonMap.put( lesson27[0], _lesson27 );
    _lessonMap.put( lesson28[0], _lesson28 );
    _lessonMap.put( lesson29[0], _lesson29 );
    _lessonMap.put( lesson30[0], _lesson30 );
    _lessonMap.put( lesson31[0], _lesson31 );
    _lessonMap.put( lesson32[0], _lesson32 );
    _lessonMap.put( lesson33[0], _lesson33 );
    _lessonMap.put( lesson34[0], _lesson34 );
    _lessonMap.put( lesson35[0], _lesson35 );
    _lessonMap.put( lesson36[0], _lesson36 );
    _lessonMap.put( lesson37[0], _lesson37 );
    _lessonMap.put( lesson38[0], _lesson38 );
    _lessonMap.put( lesson39[0], _lesson39 );
    _lessonMap.put( lesson40[0], _lesson40 );
    _lessonMap.put( lesson41[0], _lesson41 );
    _lessonMap.put( lesson42[0], _lesson42 );
    _lessonMap.put( lesson43[0], _lesson43 );
    _lessonMap.put( lesson44[0], _lesson44 );
    _lessonMap.put( lesson45[0], _lesson45 );
    _lessonMap.put( lesson46[0], _lesson46 );
    _lessonMap.put( lesson47[0], _lesson47 );
    _lessonMap.put( lesson48[0], _lesson48 );
    _lessonMap.put( lesson49[0], _lesson49 );
    _lessonMap.put( lesson50[0], _lesson50 );
    _lessonMap.put( lesson51[0], _lesson51 );
    _lessonMap.put( lesson52[0], _lesson52 );
    _lessonMap.put( lesson53[0], _lesson53 );
    _lessonMap.put( lesson54[0], _lesson54 );
    _lessonMap.put( lesson55[0], _lesson55 );

    _lessonList = new ArrayList<Lesson>();
    _lessonList.add( new Lesson( R.array.lesson_1, lesson1[0], lesson1[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_2, lesson2[0], lesson2[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_3, lesson3[0], lesson3[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_4, lesson4[0], lesson4[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_5, lesson5[0], lesson5[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_6, lesson6[0], lesson6[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_7, lesson7[0], lesson7[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_8, lesson8[0], lesson8[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_9, lesson9[0], lesson9[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_10, lesson10[0], lesson10[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_11, lesson11[0], lesson11[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_12, lesson12[0], lesson12[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_13, lesson13[0], lesson13[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_14, lesson14[0], lesson14[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_15, lesson15[0], lesson15[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_16, lesson16[0], lesson16[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_17, lesson17[0], lesson17[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_18, lesson18[0], lesson18[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_19, lesson19[0], lesson19[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_20, lesson20[0], lesson20[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_21, lesson21[0], lesson21[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_22, lesson22[0], lesson22[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_23, lesson23[0], lesson23[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_24, lesson24[0], lesson24[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_25, lesson25[0], lesson25[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_26, lesson26[0], lesson26[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_27, lesson27[0], lesson27[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_28, lesson28[0], lesson28[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_29, lesson29[0], lesson29[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_30, lesson30[0], lesson30[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_31, lesson31[0], lesson31[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_32, lesson32[0], lesson32[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_33, lesson33[0], lesson33[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_34, lesson34[0], lesson34[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_35, lesson35[0], lesson35[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_36, lesson36[0], lesson36[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_37, lesson37[0], lesson37[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_38, lesson38[0], lesson38[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_39, lesson39[0], lesson39[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_40, lesson40[0], lesson40[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_41, lesson41[0], lesson41[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_42, lesson42[0], lesson42[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_43, lesson43[0], lesson43[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_44, lesson44[0], lesson44[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_45, lesson45[0], lesson45[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_46, lesson46[0], lesson46[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_47, lesson47[0], lesson47[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_48, lesson48[0], lesson48[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_49, lesson49[0], lesson49[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_50, lesson50[0], lesson50[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_51, lesson51[0], lesson51[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_52, lesson52[0], lesson52[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_53, lesson53[0], lesson53[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_54, lesson54[0], lesson54[1] ) );
    _lessonList.add( new Lesson( R.array.lesson_55, lesson55[0], lesson55[1] ) );
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    // display options
    ArrayList<Lesson> optionsList = new ArrayList<Lesson>();
    optionsList.add( new Lesson( 0, getString( R.string.start_new_session ), null ) );

    if ( !areSharedPreferencesEmpty() )
    {
      optionsList.add( new Lesson( 0, getString( R.string.continue_with_saved_session ), null ) );
    }
    optionsList.addAll( getSortedLessonList() );

    HomeArrayAdapter adapter = new HomeArrayAdapter( this, optionsList );
    setListAdapter( adapter );
  }

  private List<Lesson> getSortedLessonList()
  {
    List<Lesson> lessonList = new ArrayList<Lesson>( _lessonList );
    Collections.sort( lessonList, new Comparator<Lesson>()
    {
      @Override
      public int compare( Lesson lesson1, Lesson lesson2 )
      {
        return Integer.valueOf( lesson1.getLessonNumber().substring( 7 ) ).compareTo(
          Integer.valueOf( lesson2.getLessonNumber().substring( 7 ) ) );
      }
    } );
    return lessonList;
  }

  private List<String> getLessonNameList()
  {
    List<String> lessonNameList = new ArrayList<String>( _lessonMap.keySet() );
    Collections.sort( lessonNameList, new Comparator<String>()
    {
      @Override
      public int compare( String s1, String s2 )
      {
        return Integer.valueOf( s1.substring( 7 ) ).compareTo( Integer.valueOf( s2.substring( 7 ) ) );
      }
    } );
    return lessonNameList;
  }

  /**
   * Returns true if SharedPreferences are empty for FlashCardActivity.PREF_NAME.
   *
   * @return
   */
  private boolean areSharedPreferencesEmpty()
  {
    SharedPreferences settings = getSharedPreferences( FlashCardActivity.PREF_NAME, 0 );
    Map<String, ?> preferenceMap = settings.getAll();

    return preferenceMap.isEmpty();
  }

  @Override
  public boolean onCreateOptionsMenu( Menu menu )
  {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate( R.menu.home_menu, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item )
  {
    switch ( item.getItemId() )
    {
      case R.id.info:
        Intent infoIntent = new Intent( HomeActivity.this, InfoActivity.class );
        startActivity( infoIntent );
        return true;
      case R.id.add_lessons:
        Intent addLessonsIntent = new Intent( HomeActivity.this, SettingsActivity.class );
        addLessonsIntent.putExtra( SettingsActivity.EXTRA_LESSON_NAMES, (Serializable) getLessonNameList() );
        startActivity( addLessonsIntent );
        return true;
      default:
        return super.onOptionsItemSelected( item );
    }
  }

  @Override
  protected void onListItemClick( ListView l, View v, int position, long id )
  {
    super.onListItemClick( l, v, position, id );

    Lesson lesson = (Lesson) getListAdapter().getItem( position );
    String option = lesson.getLessonNumber();

    if ( getString( R.string.start_new_session ).equals( option ) )
    {
      SharedPreferences prefs = getSharedPreferences( SettingsActivity.SETTINGS_PREF, 0 );
      Set<String> checkedLessonNameSet = prefs.getStringSet( SettingsActivity.KEY_CHECKED_LESSON_NAME_SET, new HashSet<String>() );

      if ( checkedLessonNameSet.isEmpty() )
      {
        Toast.makeText( getBaseContext(), "Add Lessons from Menu", Toast.LENGTH_LONG ).show();
      }
      else
      {
        // lesson ids of lessons checked in settings
        ArrayList<Integer> lessonIds = new ArrayList<Integer>();

        for ( String checkedLessonName : checkedLessonNameSet )
        {
          int[] lessonArray = _lessonMap.get( checkedLessonName );
          lessonIds.addAll( Util.append( lessonArray ) );
        }

        startSession( false, lessonIds );
      }
    }
    else if ( getString( R.string.continue_with_saved_session ).equals( option ) )
    {
      List<int[]> lessons = new ArrayList<int[]>( _lessonMap.values() );
      int[][] lessonArray = new int[lessons.size()][];

      for ( int i = 0; i < lessons.size(); i++ )
      {
        lessonArray[i] = lessons.get( i );
      }

      startSession( true, Util.append( lessonArray ) );
    }
    else // launch LessonActivity
    {
      int[] lessonArray = _lessonMap.get( option );
      Intent intent = new Intent( this, LessonActivity.class );

      for ( int i = 0; i < lessonArray.length; i++ )
      {
        intent.putExtra( LessonActivity.EXTRA_LESSON_RES_ID + i, lessonArray[i] );
      }

      intent.putExtra( LessonActivity.EXTRA_NUM_LESSONS, lessonArray.length );
      intent.putExtra( LessonActivity.EXTRA_STRING_ARRAY_ID, lesson.getStringArrayId() );
      startActivity( intent );
    }
  }

  private void startSession( final boolean doContinueWithSavedSession, final ArrayList<Integer> lessonIds )
  {
    // if user wants to start a new session and there is already a saved session, warn user that
    // if they start a new session, the saved one will be deleted
    if ( !doContinueWithSavedSession && !areSharedPreferencesEmpty() )
    {
      AlertDialog.Builder builder = new AlertDialog.Builder( HomeActivity.this );
      builder.setMessage( "Are you sure you want to start a new session? If you do, your saved session will be lost." )
        .setCancelable( false )
        .setPositiveButton( "Yes", new DialogInterface.OnClickListener()
        {
          public void onClick( DialogInterface dialog, int id )
          {
            doStartSession( doContinueWithSavedSession, lessonIds );
          }
        } )
        .setNegativeButton( "No", new DialogInterface.OnClickListener()
        {
          public void onClick( DialogInterface dialog, int id )
          {
            // do nothing
          }
        } );
      AlertDialog alert = builder.create();
      alert.show();
    }
    else
    {
      doStartSession( doContinueWithSavedSession, lessonIds );
    }
  }

  private void doStartSession( boolean doContinueWithSavedSession, ArrayList<Integer> lessonIds )
  {
    Intent intent = new Intent( HomeActivity.this, FlashCardActivity.class );
    intent.putExtra( FlashCardActivity.EXTRA_CONTINUE_WITH_SAVED_SESSION, doContinueWithSavedSession );
    intent.putIntegerArrayListExtra( EXTRA_RESOURCE_IDS, lessonIds );
    startActivity( intent );
  }
}
