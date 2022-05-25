package homework6;

import homework6.db.dao.CategoriesMapper;
import homework6.db.model.Categories;
import homework6.db.model.CategoriesExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



public class ExampleMain {

    public static void main( String[] args ) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);
        CategoriesExample example = new CategoriesExample();
        example.createCriteria().andIdEqualTo(1L);
        List<Categories> list = categoriesMapper.selectByExample(example);
        System.out.println(categoriesMapper.countByExample(example));

        Categories categories = new Categories();
        categories.setTitle("test");
        categoriesMapper.insert(categories);
        session.commit();

        CategoriesExample example2 = new CategoriesExample();
        example2.createCriteria().andTitleLike("test");
        List<Categories> list2 = categoriesMapper.selectByExample(example2);
        Categories categories2 = list2.get(0);
        categories2.setTitle("test100");
        categoriesMapper.updateByPrimaryKey(categories2);
        session.commit();

        categoriesMapper.deleteByPrimaryKey(categories2.getId());
        session.commit();

        session.close();

    }
}
