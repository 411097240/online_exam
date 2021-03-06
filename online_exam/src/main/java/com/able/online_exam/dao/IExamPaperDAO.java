package com.able.online_exam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.able.online_exam.pojo.ChoiceQuestion;
import com.able.online_exam.pojo.ExamPaper;
import com.able.online_exam.pojo.QuestionAnswer;
import com.able.online_exam.pojo.User;


public interface IExamPaperDAO {
	//通过用户名和state找试卷
	List<Map<String,String>>  findExamPaperByUser(String userName,int state);
	//通过试卷id找到试卷所有题目
	List<Map<String,String>>  findExamPaperByPaperId(int paperId);
	//批量插入用户的试卷答案信息
	int answerInsert(@Param("list")List<QuestionAnswer> answerlist);
	//通过用户名和试卷id查询答案表，判断用户是否已经提交过卷子
	int cheakSubmit(String userName,int paperId);
	//教师端通过试卷分数（-1）找到所有未批改的试卷
	List<Map<String,String>>  findPaperListNotCorrecting(int score);
	//指定用户和试卷id的所有选择题答案信息(老师端)
	List<Map<String,String>>  findSubmitPaperMessage(String userName,int paperId);
	//提交试卷分数
	int submitScore(String userName,int paperId,int totalScore);
	//学生提交试卷时将user_paper表的state更新为1,score更新为-1
	int updateUserPaper(String userName,int paperId);
	//找到最大的paperId
	List<ExamPaper> findMaxPaperId();
	//批量更新选择题表
	int questionInsert(@Param("questionList")List<ChoiceQuestion> choiceQuestions);
	//在试卷表添加一张试卷信息
	int insertPaper(String teacherName,String title,int id);
	//在用户表中找所有权限为1的用户
	List<User> findAllStudent();
	//批量更新user_paper表
	int insertUserPaper(@Param("users")List<User> users,int paperId);
}
