/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.albumappapiusers.service;


import com.example.albumappapiusers.data.AlbumEntity;

import java.util.List;

public interface AlbumsService {
    List<AlbumEntity> getAlbums(String userId);
}
