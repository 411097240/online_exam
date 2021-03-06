package com.able.online_exam.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.able.online_exam.dao.IExamPaperDAO;
import com.able.online_exam.pojo.ChoiceQuestion;
import com.able.online_exam.pojo.QuestionAnswer;
import com.able.online_exam.pojo.User;
import com.able.online_exam.service.IFindPaperService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
@Service
public class FindPaperServiceImpl implements IFindPaperService{
	@Resource
	private IExamPaperDAO findpaper;
	@Override
	//通过用户名和state找试卷信息
	public JSONArray findPaper(String userName,int state) {
		
	    List<Map<String,String>> ls = findpaper.findExamPaperByUser(userName,state);
		String s = JSON.toJSONString(ls);
		JSONArray jsonArray = JSONArray.parseArray(s);
		System.out.println(jsonArray);		
		return jsonArray;
	}
	
	@Override
	//通过试卷id找到试卷所有题目
	public JSONArray findExamPaperByPaperId(int paperId) {
		List<Map<String,String>> ls = findpaper.findExamPaperByPaperId(paperId);
		String s = JSON.toJSONString(ls);
		JSONArray jsonArray = JSONArray.parseArray(s);
		System.out.println(jsonArray);		
		return jsonArray;
	}

	@Override
	//判断是否试卷提交成功
	public boolean isSubmit(List<QuestionAnswer> answerlist) {
		int count=findpaper.answerInsert(answerlist);
		if(count>=0){
			return true;
		}
		return false;
		
	}

	@Override
	//判断试卷是否重复提交
	public boolean cheakSubmit(String userName, int paperId) {
		int count =findpaper.cheakSubmit(userName, paperId);
		if(count>0){
			return true;
		}
		return false;
	}
	//老师端找未批改试卷列表
	@Override
	public JSONArray findPaperListNotCorrecting(int score) {
		List<Map<String,String>> ls = findpaper.findPaperListNotCorrecting(score);
		String s = JSON.toJSONString(ls);
		JSONArray jsonArray = JSONArray.parseArray(s);
		System.out.println(jsonArray);		
		return jsonArray;
	}
	
	
	//指定用户和试卷id的所有选择题答案信息
	@Override
	public JSONArray findSubmitPaperMessage(String userName, int paperId) {
		List<Map<String,String>> ls = findpaper.findSubmitPaperMessage(userName, paperId);
		String s = JSON.toJSONString(ls);
		JSONArray jsonArray = JSONArray.parseArray(s);
		System.out.println(jsonArray);		
		return jsonArray;
	}
	//提交试卷成绩
	@Override
	public boolean submitScore(String userName, int paperId, int totalScore) {
		if(findpaper.submitScore(userName, paperId, totalScore)>0){
			return true;
		}
		return false;
	}
	//学生提交试卷时将user_paper表的state更新为1,score更新为-1
	@Override
	public boolean updateUserPaper(String userName, int paperId) {
		if(findpaper.updateUserPaper(userName, paperId)>0){
			return true;
		}
		return false;
	}

	
	//老师新建试题
	@Override
	@Transactional  
	public boolean addQuestion(List<ChoiceQuestion> choiceQuestion,String teacherName,String title) {
		//找到数据库最大的paperId
		//System.out.println(findpaper.findMaxPaperId().get(0).getId());
		//批量插入题目
		int maxPaperId = findpaper.findMaxPaperId().get(0).getId()+1;
		for(int i=0;i<choiceQuestion.size();i++){
		choiceQuestion.get(i).setCqPaperId(maxPaperId);
		}
		//在试卷表添加一张试卷信息
		int count = findpaper.insertPaper(teacherName,title,maxPaperId);
		//新增用户试卷关联表user_paper数据,先找到所有学生用户再批量更新
		List<User> users = findpaper.findAllStudent();
		int num = findpaper.insertUserPaper(users, maxPaperId);
		if(findpaper.questionInsert(choiceQuestion)>0&&count>0&&num>0)
		return true;
		return false;
	}

	

}
