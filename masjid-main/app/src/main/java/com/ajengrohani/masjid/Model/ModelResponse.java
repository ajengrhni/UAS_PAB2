package com.ajengrohani.masjid.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelMasjid> data;

    public String getKode(){return kode;}
    public String getPesan(){return pesan;}

    public List<ModelMasjid> getData(){
        return data;
    }
}
