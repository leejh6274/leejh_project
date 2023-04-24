package com.ljh.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ljh.exam.demo.repository.ArticleRepository;
import com.ljh.exam.demo.utill.Ut;
import com.ljh.exam.demo.vo.Article;
import com.ljh.exam.demo.vo.ResultData;

@Service
public class ArticleService {
   private ArticleRepository articleRepository;

   public ArticleService(ArticleRepository articleRepository) {
      this.articleRepository = articleRepository;
   }

   public Article getForPrintArticle(int actorId, int id) {
	   Article article = articleRepository.getForPrintArticle(id);
	   
	   updateForPrintData(actorId, article);
	   
      return article;
   }

   public List<Article> getForPrintArticles(int actorId, int boardId, int itemsCountInAPage, int page) {
	   
	   /*
	    SELECT *
	    FROM article
	    WHERE boardId = 1
	    ORDER BY id DESC
	    LIMIT 0, 10
	    */
	   
	   int limitStart = (page - 1) * itemsCountInAPage;     //limitStart : 2페이지라면 11부터 20까지의 게시물을 가져와야한다. 쿼리문(LIMIT)을 작성하기위한 변수
	   int limitTake = itemsCountInAPage;					//limitTake : 페이지당 가져올 게시물의 수, 쿼리문(LIMIT)을 작성하기위한 변수
	   
	   
	   List<Article> articles = articleRepository.getForPrintArticles(boardId, limitStart, limitTake);
	   
	   for(Article article : articles) {
		   updateForPrintData(actorId, article);
	   }
      return articles;
   }
   
   public void updateForPrintData(int actorId, Article article) {
	   if (article == null){
		   return;
	   }
	   
	   ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
	   article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
	   
	   ResultData actorCanModifyRd = actorCanModify(actorId, article);
	   article.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
	   
   }
   
   public ResultData actorCanDelete(int actorId, Article article) {
	   
	   if(article == null) {
		   return ResultData.from("S-1","게시물이 존재하지 않습니다.");
	   }
	   
	   if(article.getMemberId() != actorId) {
		   return ResultData.from("F-2","권한이 없습니다.");
	   }
	   
	   return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
   }

   public ResultData<Integer> writeArticle(int memberId, int boardId, String title, String body) {
       articleRepository.writeArticle(memberId, boardId, title, body);
       int id = articleRepository.getLastInsertId();
       
       return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id),"id", id);
   }

   public void deleteArticle(int id) {
      articleRepository.deleteArticle(id);
   }

   public ResultData<Article> modifyArticle(int id, String title, String body) {
	  
      articleRepository.modifyArticle(id, title, body);
      
      Article article = getForPrintArticle(0,id);
      
      return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id), "article", article);
   }

   public ResultData actorCanModify(int actorId, Article article) {
	   
	   if(article == null) {
		   return ResultData.from("F-1", "권한이 없습니다.");
	}
	   if(article.getMemberId() != actorId) {
		   return ResultData.from("F-2", "권한이 없습니다.");
	}
	   return ResultData.from("S-1", "수정 가능합니다.");
}

   public int getArticlesCount(int boardId) {
		return articleRepository.getArticlesCount(boardId);
	}
}