package com.example.testmvp.presenter.utils;

import android.app.Activity;

import androidx.annotation.ArrayRes;

import com.example.testmvp.BuildConfig;
import com.example.testmvp.R;
import com.example.testmvp.model.content.ModelMenu;
import com.example.testmvp.model.content.ModelMore;
import com.example.testmvp.model.content.login.Item;
import com.example.testmvp.model.content.login.MenuNavbar;
import com.example.testmvp.model.content.login.ModelDataLogin;
import com.example.testmvp.model.content.login.UserAccess;
import com.example.testmvp.model.response.ResponseLogin;
import com.example.testmvp.presenter.session.SessionMenu;
import com.example.testmvp.presenter.session.SessionUser;
import com.example.testmvp.presenter.session.SessionVersion;
import com.google.gson.Gson;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import connection.rxconnection.session.SessionLogin;

@EBean
public class UtilsUser {
    @RootContext
    protected Activity activity;

    @Bean
    protected UtilsMenu utilsMenu;

    // Dummy
    public void setData() {
        ArrayList<MenuNavbar> listNavbar = new ArrayList<>();
        listNavbar.add(new MenuNavbar("Settings", 1, 1));
        listNavbar.add(new MenuNavbar("Favorite", 2, 2));

        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Item("Settings",1));
        items1.add(new Item("Favorite",2));

        UserAccess userAccess1 = new UserAccess(items1, "Setting F");

        ArrayList<Item> items2 = new ArrayList<>();
        items2.add(new Item("Favorite",2));
        items2.add(new Item("Settings",1));

        UserAccess userAccess2 = new UserAccess(items2, "Favorite S");

        ArrayList<UserAccess> userAccess = new ArrayList<>();
        userAccess.add(userAccess1);
        userAccess.add(userAccess2);

        ModelDataLogin dataUser = new ModelDataLogin();
        dataUser.setMenuNavbars(listNavbar);
        dataUser.setUserAccess(userAccess);

        setMenu(dataUser);
    }

    public void setData(ResponseLogin responseLogin) {
        ModelDataLogin dataUser = responseLogin.getData();
        new SessionUser(activity).setData(dataUser);
        new SessionLogin(activity).setToken("Bearer " + responseLogin.getData().getToken());

        setMenu(dataUser);
    }

    @Background(delay = 100)
    protected void setMenu(ModelDataLogin dataUser) {
        if (utilsMenu.getCallback() >= utilsMenu.getMaxCallback()) {
            createMenu(dataUser.getMenuNavbars(), dataUser.getUserAccess());
        }
        else setMenu(dataUser);
    }

    @Background(delay = 100)
    protected void generate(ArrayList<MenuNavbar> listNavbar, ArrayList<UserAccess> listAccess) {
        if (utilsMenu.getCallback() >= utilsMenu.getMaxCallback()) {
            createMenu(listNavbar, listAccess);
        }
        else generate(listNavbar, listAccess);
    }

    @Background(delay = 500)
    protected void createMenu(ArrayList<MenuNavbar> listNavbar, ArrayList<UserAccess> listAccess) {
        ArrayList<ModelMenu> listMenu = new ArrayList<>();
        ArrayList<ModelMore> listMore = new ArrayList<>();

        for (MenuNavbar menuNavbar : listNavbar) {
            ModelMenu modelMenu = utilsMenu.getMenu(menuNavbar.getMobileMenuId(),
                    menuNavbar.getMobileMenuDesc());
            listMenu.add(modelMenu);
        }

        listMenu.add(utilsMenu.getMenu(0, "More"));

        // if listmenu has not only more menu, set first one as home
        if (listMenu.size() > 1) {
            listMenu.get(0).setTitle("Home");
            listMenu.get(0).setIconNav(R.drawable.ic_baseline_home_24);
        }

        for (UserAccess userAccess : listAccess) {
            ModelMore modelMore = new ModelMore();
            modelMore.setTitle(userAccess.getName());
            ArrayList<ModelMenu> listMenuMore = new ArrayList<>();
            for (Item item : userAccess.getItem()){
                ModelMenu modelMenu = utilsMenu.getMenu(item.getMobileMenuId(), item.getMobileMenuDesc());
                listMenuMore.add(modelMenu);
            }
            modelMore.setChild(listMenuMore);
            listMore.add(modelMore);
        }

        SessionMenu sessionMenu = new SessionMenu(activity);
        sessionMenu.setDataNavBar(listMenu);
        sessionMenu.setDataMore(listMore);
        new SessionVersion(activity).setVersion(BuildConfig.VERSION_CODE);
    }
}
