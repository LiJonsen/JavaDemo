# 根据方向查询题目
# 1.查询所以方向和对应的company_id【创建视图虚拟表】
CREATE VIEW v_dic AS SELECT 
	ci.company_id company_id,i.name title,i.id industry_id,count(q.id) v_count,q.id question_id
FROM tr_company_industry ci 
JOIN t_industry i 
ON ci.industry_id=i.id
LEFT JOIN t_question q 
ON q.id=ci.company_id
GROUP BY i.id
;

# 2. 统计
# SELECT v1.* FROM v1 ORDER BY v1.company_id;
SELECT sum(d.v_count) allCount,d.title,d.industry_id,COUNT(wx.id) finishedCount
FROM v_dic d
LEFT JOIN (SELECT * FROM tr_member_question mq WHERE mq.member_id=7) wx
ON wx.question_id=d.question_id
GROUP BY d.company_id 
ORDER BY d.company_id;

# 查看视图
show CREATE VIEW v_dic;
# 删除视图
drop view v_dic;