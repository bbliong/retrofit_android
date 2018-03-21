package com.example.user.ketegori_2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scholarship {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_beasiswa")
    @Expose
    private String namaBeasiswa;
    @SerializedName("nama_instantsi")
    @Expose
    private String namaInstantsi;
    @SerializedName("quota")
    @Expose
    private Integer quota;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("alamat_gambar")
    @Expose
    private String alamatGambar;
    @SerializedName("masa_berlaku")
    @Expose
    private String masaBerlaku;
    @SerializedName("facilitator_id")
    @Expose
    private Integer facilitatorId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBeasiswa() {
        return namaBeasiswa;
    }

    public void setNamaBeasiswa(String namaBeasiswa) {
        this.namaBeasiswa = namaBeasiswa;
    }

    public String getNamaInstantsi() {
        return namaInstantsi;
    }

    public void setNamaInstantsi(String namaInstantsi) {
        this.namaInstantsi = namaInstantsi;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getAlamatGambar() {
        return alamatGambar;
    }

    public void setAlamatGambar(String alamatGambar) {
        this.alamatGambar = alamatGambar;
    }

    public String getMasaBerlaku() {
        return masaBerlaku;
    }

    public void setMasaBerlaku(String masaBerlaku) {
        this.masaBerlaku = masaBerlaku;
    }

    public Integer getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(Integer facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

}