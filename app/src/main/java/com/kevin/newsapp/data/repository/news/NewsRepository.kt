package com.kevin.newsapp.data.repository.news

import com.kevin.newsapp.data.webservice.news.NewsRemoteDataSource

/**
 * database + api service
 * DB data 확인
 * if exists -> fetch data from DB
 * else -> api call -> fetch data to DB
 *
 * FETCH 주기를 설정해서 DB 데이터 파기
 */
interface NewsRepository : NewsRemoteDataSource /*TODO 위의 사항들에 대한 interface + type 지정? */