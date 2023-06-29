package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private int selectedTab = 1;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout likeLayout = findViewById(R.id.likeLayout);
        final LinearLayout notificationLayout = findViewById(R.id.notificationLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);

        final LottieAnimationView homeAnimation = findViewById(R.id.homeAnimation);
        final LottieAnimationView productAnimation = findViewById(R.id.productAnimation);
        final LottieAnimationView cartAnimation = findViewById(R.id.cartAnimation);
        final LottieAnimationView profileAnimation = findViewById(R.id.profileAnimation);
/*
        final ImageView homeImage=findViewById(R.id.homeImage);
        final ImageView likeImage=findViewById(R.id.likeImage);
        final ImageView notificationImage=findViewById(R.id.notificationImage);
        final ImageView profileImage=findViewById(R.id.profileImage);
*/
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        DrawerManager drawerManager = new DrawerManager();
        drawerManager.setupDrawer(this, drawerLayout);

        TopBarManager topBar = new TopBarManager();
        topBar.setupTopBar(this);
        topBar.setNavigationIcon(
                ContextCompat.getDrawable(this, R.drawable.menu),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START);
                        }
                    }
                }
        );



        final TextView homeTxt=findViewById(R.id.homeTxt);
        final TextView likeTxt=findViewById(R.id.likeTxt);
        final TextView notificationTxt=findViewById(R.id.notificationTxt);
        final TextView profileTxt=findViewById(R.id.profileTxt);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer,HomeFragment.class,null)
                .commit();
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab !=1){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,HomeFragment.class,null)
                            .commit();

                    likeTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    //productAnimation.setImageResource(R.drawable.ic_like);
                    //cartAnimation.setImageResource(R.drawable.ic_notification);
                    //profileAnimation.setImageResource(R.drawable.ic_profile);

                    productAnimation.setAnimation(R.raw.product);
                    cartAnimation.setAnimation(R.raw.cartv2);
                    profileAnimation.setAnimation(R.raw.profile);


                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeTxt.setVisibility(View.VISIBLE);
                    //homeImage.setImageResource(R.drawable.ic_home_selected);
                    homeAnimation.setAnimation(R.raw.home);
                    homeAnimation.playAnimation();
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    selectedTab=1;

                }
            }
        });

        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab !=2){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,LikeFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);
/*
                    homeImage.setImageResource(R.drawable.ic_home);
                    notificationImage.setImageResource(R.drawable.ic_notification);
                    profileImage.setImageResource(R.drawable.ic_profile);
*/
                    homeAnimation.setAnimation(R.raw.home);
                    cartAnimation.setAnimation(R.raw.cartv2);
                    profileAnimation.setAnimation(R.raw.profile);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    likeTxt.setVisibility(View.VISIBLE);
                    //likeImage.setImageResource(R.drawable.ic_like_selected);
                    productAnimation.setAnimation(R.raw.product);
                    productAnimation.playAnimation();
                    likeLayout.setBackgroundResource(R.drawable.round_back_like_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    likeLayout.startAnimation(scaleAnimation);

                    selectedTab=2;

                }
            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab !=3){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,NotificationFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    likeTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);
/*
                    homeImage.setImageResource(R.drawable.ic_home);
                    likeImage.setImageResource(R.drawable.ic_like);
                    profileImage.setImageResource(R.drawable.ic_profile);
*/
                    homeAnimation.setAnimation(R.raw.home);
                    productAnimation.setAnimation(R.raw.product);
                    profileAnimation.setAnimation(R.raw.profile);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    notificationTxt.setVisibility(View.VISIBLE);
                    //notificationImage.setImageResource(R.drawable.ic_notification_selected);
                    cartAnimation.setAnimation(R.raw.cartv2);
                    cartAnimation.playAnimation();

                    notificationLayout.setBackgroundResource(R.drawable.round_back_notification_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    notificationLayout.startAnimation(scaleAnimation);

                    selectedTab=3;

                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab !=4){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,ProfileFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    likeTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);
/*
                    homeImage.setImageResource(R.drawable.ic_home);
                    likeImage.setImageResource(R.drawable.ic_like);
                    notificationImage.setImageResource(R.drawable.ic_notification);
*/
                    homeAnimation.setAnimation(R.raw.home);
                    productAnimation.setAnimation(R.raw.product);
                    cartAnimation.setAnimation(R.raw.cartv2);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    profileTxt.setVisibility(View.VISIBLE);
                    //profileImage.setImageResource(R.drawable.ic_profile_selected);
                    profileAnimation.setAnimation(R.raw.profile);
                    profileAnimation.playAnimation();
                    profileLayout.setBackgroundResource(R.drawable.round_back_profile_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    profileLayout.startAnimation(scaleAnimation);

                    selectedTab=4;

                }
            }
        });

    }
    public void goTools(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Tools.class);
        startActivity(intent);
    }
    public void goMyAccount(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, MyAccount.class);
        startActivity(intent);
    }
}