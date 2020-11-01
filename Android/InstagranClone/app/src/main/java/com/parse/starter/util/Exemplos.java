///*
// * Copyright (c) 2015-present, Parse, LLC.
// * All rights reserved.
// *
// * This source code is licensed under the BSD-style license found in the
// * LICENSE file in the root directory of this source tree. An additional grant
// * of patent rights can be found in the PATENTS file in the same directory.
// */
//package com.parse.starter.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.parse.LogInCallback;
//import com.parse.ParseException;
//import com.parse.ParseUser;
//import com.parse.SignUpCallback;
//import com.parse.starter.R;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        //Cadastro de Usuario
//        final ParseUser user = new ParseUser();
//        user.setUsername("MeninoBom");
//        user.setPassword("123456");
//        user.setEmail("mb@gmail.com");
//
//
//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null){
//                    Log.i("USER","SUCESS");
//                }
//                else {
//                    Log.i("USER",e.getMessage());
//
//                }
//            }
//        });
//
//
//        //Buscar usuario logafo
//        if(ParseUser.getCurrentUser() != null){
//            Log.i("USER","LOGADO");
//
//        }
//        else {
//            Log.i("USER","N LOGADO");
//
//        }
//
//        //Logour USUARIO
//        if(ParseUser.getCurrentUser() != null){
//            Log.i("USER","LOGADO");
//            ParseUser.logOut();
//        }
//        else {
//            Log.i("USER","N LOGADO");
//
//        }
//
//        //Buscar usuario logafo
//        if(ParseUser.getCurrentUser() != null){
//            Log.i("USER","LOGADO");
//
//        }
//        else {
//            Log.i("USER","N LOGADO");
//        }
//
//        ParseUser.logInInBackground("MeninoBom", "12456", new LogInCallback() {
//            @Override
//            public void done(ParseUser user, ParseException e) {
//                if(e == null){
//                    Log.i("USER","LOGADO");
//                }
//                else {
//                    Log.i("USER",e.getMessage());
//                }
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        //Salvando dados
////        ParseObject pontuacao = new ParseObject("Pontuacao");
////
////        pontuacao.put("nome","nome qualquer 004");
////        pontuacao.put("pontos",98989);
////
////        pontuacao.saveInBackground(new SaveCallback() {
////            @Override
////            public void done(ParseException e) {
////                if(e == null){
////                    Log.i("SAVE","Salvo com balalal");
////                }
////                else {
////                    Log.i("SAVE","bla bala");
////
////                }
////            }
////        });
//
////        //Recuperando Objetos
////        ParseQuery<ParseObject> consulta = ParseQuery.getQuery("Pontuacao");
////        consulta.getInBackground("BaH1oNFtaG", new GetCallback<ParseObject>() {
////            @Override
////            public void done(ParseObject object, ParseException e) {
////                if(e == null){
////                    object.put("pontos",5000);
////                    object.put("nome","Menino Bom");
////                    object.saveInBackground();
////                }
////                else {
////                    Log.i("SAVE","bla bla bla");
////                }
////            }
////        });
//
////        //Filtrando Objetos
////        ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Pontuacao");
////
////        filtro.whereGreaterThan("pontos", 5000).addDescendingOrder("nome").setLimit(1);
////
////        filtro.findInBackground(new FindCallback<ParseObject>() {
////            @Override
////            public void done(List<ParseObject> objects, ParseException e) {
////                if (e == null) {
////                    for (ParseObject po : objects) {
////                        String s = String.format(Locale.getDefault(), "Nome :%s , Pontuação: %s", po.get("nome"), po.get("pontos"));
////                        Log.i("NOME>510", s);
////                    }
////
////                } else {
////                    Log.i("SAVE", e.getMessage());
////                }
////            }
////        });
//    }
//}
//
//
//
//
//
