package com.android.app.quizapp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.app.quizapp.MainActivity;
import com.android.app.quizapp.R;
import com.android.app.quizapp.about.about;
import com.android.app.quizapp.activities.QuizActivity;
import com.android.app.quizapp.adapter.CategoriesAdapter;
import com.android.app.quizapp.quizdb.Category;
import com.android.app.quizapp.quizdb.User;
import com.android.app.quizapp.viewModel.CategoryViewModel;
import com.android.app.quizapp.viewModel.QuizViewModel;
import com.android.app.quizapp.viewModel.UserViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserQuiz extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CategoryViewModel viewModel;
    LiveData<List<Category>> allCategories;
    ListView categoriesList;
    List<Category> list;
    NavigationView navigationView;
    Bundle bundle;

    public static final String FB = "https://www.facebook.com/profile.php?id=100008792711291";
    public static final String EMAIL = "https://mail.google.com/mail/u/0/?tab=wm#inbox";
    public static final String LINKEDIN = "https://www.linkedin.com/in/abdul-jabbar-7b1401132/";

    User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_quiz);

        initUI();
        bundle = getIntent().getExtras();

        allCategories = viewModel.getAllCategories();

        allCategories.observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                CategoriesAdapter adapter = new CategoriesAdapter();
                adapter.setCategories(categories);
                list = categories;
                categoriesList.setAdapter(adapter);
            }
        });

        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveTo = new Intent(UserQuiz.this, QuizActivity.class);
                moveTo.putExtra("Title", list.get(position).getTitle());
                startActivity(moveTo);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_fb:
                        Intent gotoFB = new Intent(Intent.ACTION_VIEW);
                        gotoFB.setData(Uri.parse(FB));
                        startActivity(gotoFB);
                        break;

                    case R.id.nav_message:
                        Intent gotoMail = new Intent(Intent.ACTION_VIEW);
                        gotoMail.setData(Uri.parse(EMAIL));
                        startActivity(gotoMail);
                        break;

                    case R.id.nav_linkedIn:
                        Intent gotoLinkeIn = new Intent(Intent.ACTION_VIEW);
                        gotoLinkeIn.setData(Uri.parse(LINKEDIN));
                        startActivity(gotoLinkeIn);
                        break;

                    case R.id.nav_admin:
                        startActivity(new Intent(UserQuiz.this, about.class));
                        break;

                    case R.id.nav_share:
                        Intent share = new Intent();
                        share.setAction(Intent.ACTION_SEND);
                        share.putExtra(Intent.EXTRA_TEXT,
                                "Hey check out my app at:");
                        share.setType("text/plain");
                        startActivity(share);
                        break;

                    case R.id.nav_send:
                        Toast.makeText(UserQuiz.this, "Send", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.drawer_layout);
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation);
        categoriesList = findViewById(R.id.user_categories);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private class ImageLoadingAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String userEmail = strings[0];
//           User currentUser = user_viewModel.getCurrentUser(userEmail);

            byte[] bitmapdata = currentUser.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
//            profileImage.setImageBitmap(bitmap);
        }
    }
}