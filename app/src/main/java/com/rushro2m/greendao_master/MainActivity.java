package com.rushro2m.greendao_master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rushro2m.greendao_master.bean.DaoSession;
import com.rushro2m.greendao_master.bean.MovieBean;
import com.rushro2m.greendao_master.bean.MovieBeanDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler_main);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_insert:
                insertMovie();
                break;
            case R.id.main_query:
                queryMovie();
                break;
            case R.id.main_delete:
                deleteMovie();
                break;
            case R.id.main_update:
                updateMovie();
                break;
        }
    }


    //增
    private void insertMovie() {
        MovieBean movie = new MovieBean();
        movie.setTitle("这是第" + ++i + "条数据。");
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        MovieBeanDao movieBeanDao = daoSession.getMovieBeanDao();
        movieBeanDao.insert(movie);
        queryMovieBean();
    }


    //删
    private void deleteMovie() {
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        MovieBeanDao movieBeanDao = daoSession.getMovieBeanDao();
        Query<MovieBean> query = movieBeanDao.queryBuilder()
                .where(MovieBeanDao.Properties.Id.eq(10))
                .build();
        MovieBean movieBean = query.unique();
        if (movieBean != null) {
            movieBeanDao.delete(movieBean);
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "此条数据不存在", Toast.LENGTH_SHORT).show();
        }
        queryMovieBean();
    }

    //改
    private void updateMovie() {
        //先查询数据，在修改数据，然后更新到数据库中去
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();

        MovieBeanDao movieBeanDao = daoSession.getMovieBeanDao();
        Query<MovieBean> movieBeanQuery = movieBeanDao.queryBuilder()
                .where(MovieBeanDao.Properties.Id.eq(5))
                .build();
        List<MovieBean> beanList = movieBeanQuery.list();
        if (beanList != null) {
            for (MovieBean movie : beanList) {
                movie.setTitle("这条是更新的数据。");
                movieBeanDao.update(movie);
            }
            Toast.makeText(this, "数据更新完成", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "数据不存在", Toast.LENGTH_SHORT).show();
        }
        queryMovieBean();
    }

    //查
    private void queryMovie() {
        //先查询，再修改数据，然后更新到数据库中
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        MovieBeanDao movieBeanDao = daoSession.getMovieBeanDao();
        Query<MovieBean> beanQuery = movieBeanDao.queryBuilder()
                .where(MovieBeanDao.Properties.Id.eq(15))
                .build();
        MovieBean movieBean = beanQuery.unique();
        if (movieBean != null) {
            Toast.makeText(this, "数据查询成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "这条数据不存在", Toast.LENGTH_SHORT).show();
        }
        queryMovieBean();
    }


    private void queryMovieBean() {
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        MovieBeanDao movieBeanDao = daoSession.getMovieBeanDao();
        Query<MovieBean> query = movieBeanDao.queryBuilder().build();
        MyAdapter myAdapter = new MyAdapter(query.list(), this);
        recycler.setAdapter(myAdapter);
    }
}
