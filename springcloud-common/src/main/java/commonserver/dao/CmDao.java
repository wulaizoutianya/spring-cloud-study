package commonserver.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//注解了@Repository的类上如果数据库操作中抛出了异常，就能对其进行处理，转而抛出的是翻译后的spring专属数据库异常，方便我们对异常进行排查处理
@Repository
//加入 @Transactional 注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库。
@Transactional
public class CmDao {
	
	/**
     * @Autowired默认按类型装配（这个注解是属于spring的），
     * 默认情况下必须要求依赖对象必须存在，如果要允许null值，
     * 可以设置它的required属性为false，如：@Autowired(required=false) ，
     * 如果我们想使用名称装配可以结合@Qualifier注解进行使用 @Autowired()@Qualifier("baseDao")
     * @Resource（这个注解属于J2EE的），默认按照名称进行装配，名称可以通过name属性进行指定，
     * 如果没有指定name属性，当注解写在字段上时，默认取字段名进行安装名称查找，
     * 如果注解写在setter方法上默认取属性名进行装配。当找不到与名称匹配的bean时才按照类型进行装配。
     * 但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
     */
    @Autowired
    @Qualifier("sqlSessionWriteTemplate")
    private SqlSessionTemplate sqlSessionWriteTemplate;

    @Autowired
    @Qualifier("sqlSessionReadTemplate")
    private SqlSessionTemplate sqlSessionReadTemplate;
    
    /**
     * 插入一个对象
     * 
     * @param sql 	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param obj	需要插入的单个对象
     * @return		返回插入结果
     * @throws Exception	异常
     */
    public int insertOneObject(String sql, Object obj) throws Exception {
        return sqlSessionWriteTemplate.insert(sql, obj);
    }
    
    /**
     * 插入多个对象
     * 
     * @param sql 	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param objs	需要插入的对象集合
     * @return		返回插入结果
     * @throws Exception	异常
     */
    @SuppressWarnings("rawtypes")
	public int insertManyObject(String sql, List objs) throws Exception {
    	 SqlSessionFactory sqlSessionFactory = sqlSessionWriteTemplate.getSqlSessionFactory();
         //批量执行器
         SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
         try{
             if(objs != null){
                 for(int i = 0, size = objs.size(); i < size; i++){
                     sqlSession.insert(sql, objs.get(i));
                 }
                 sqlSession.flushStatements();
                 sqlSession.commit();
                 sqlSession.clearCache();
             }
         } finally{
             sqlSession.close();
         }
        return objs.size();
    }
    
    /**
     * 更新一个对象
     * 
     * @param sql 	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param obj	需要更新的单个对象
     * @return		返回更新结果
     * @throws Exception	异常
     */
    public int updateOneObject(String sql, Object obj) throws Exception {
        return sqlSessionWriteTemplate.update(sql, obj);
    }

    /**
     * 更新多个对象
     * 
     * @param sql 	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param objs	需要更新的对象集合
     * @return		返回更新结果
     * @throws Exception	异常
     */
    @SuppressWarnings("rawtypes")
	public void batchUpdate(String sql, List objs)throws Exception{
        SqlSessionFactory sqlSessionFactory = sqlSessionWriteTemplate.getSqlSessionFactory();
        //批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        try{
            if(objs != null){
                for(int i = 0, size = objs.size(); i < size; i++){
                    sqlSession.update(sql, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        } finally{
            sqlSession.close();
        }
    }
    
    /**
     * 删除一个对象
     * 
     * @param sql 	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param obj	需要删除的单个对象
     * @return		返回删除结果
     * @throws Exception	异常
     */
    public int deleteOneObject(String sql, Object obj) throws Exception {
        return sqlSessionWriteTemplate.delete(sql, obj);
    }
    
    /**
     * 删除多个对象
     * 
     * @param sql 	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param objs	需要删除的对象集合
     * @return		返回删除结果
     * @throws Exception	异常
     */
    @SuppressWarnings("rawtypes")
	public int deleteManyObject(String sql, List objs) throws Exception {
        return sqlSessionWriteTemplate.delete(sql, objs);
    }
    
    /**
     * 查找返回一个对象
     * 
     * @param sql	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param obj	查询条件
     * @return		查询结果
     * @throws Exception	异常
     */
    public Object selectOneObject(String sql, Object obj){
        return sqlSessionReadTemplate.selectOne(sql, obj);
    }
    /**
     * 查找返回多个对象
     * 
     * @param sql	需要执行的sql，必须和mapper标签的namespace属性的值相对应
     * @param obj	查询条件
     * @return		查询结果
     * @throws Exception	异常
     */
    @SuppressWarnings("rawtypes")
	public List selectListObject(String sql, Object obj){
        return sqlSessionReadTemplate.selectList(sql, obj);
    }
}
