#### 一、概述

> Spring是一个IOC （DI）& AOP容器框架
>
> 
>
> 特点：
>
> * 非侵入式：基于Spring开发的应用中的对象可以不依赖于Spring的API（即：使用某个功能，必须先实现某个接口的抽象方法，称作：侵入式）；
> * 依赖注入：DI——Dependency Injection，`反转控制(IOC)`最经典的实现。
> * 面向切面编程：Aspect Oriented Programming——AOP
> * 容器：Spring是一个容器，因为它包含并且管理应用对象的生命周期
> * 组件化：Spring实现了使用简单的组件配置组合成一个复杂的应用。在 Spring 中可以使用XML和Java注解组合这些对象。
> * 一站式：在IOC和AOP的基础上可以整合各种企业应用的开源框架和优秀的第三方类库（实际上Spring 自身也提供了表述层的SpringMVC和持久层的Spring JDBC。



##### 1.1 IOC（Inversion of Control）反转控制

在应用程序中的组件需要获取资源时，传统的方式是组件主动的从容器中获取所需要的资源，在这样的模式下开发人员往往需要知道在具体容器中特定资源的获取方式，增加了学习成本，同时降低了开发效率。

反转控制的思想完全颠覆了应用程序组件获取资源的传统方式：反转了资源的获取方向——改由容器主动的将资源推送给需要的组件，开发人员不需要知道容器是如何创建资源对象的，只需要提供接收资源的方式即可，极大的降低了学习成本，提高了开发的效率。这种行为也称为查找的被动形式。

##### 1.2 DI（Dependency Injection） 依赖注入

IOC的另一种表述方式：即组件以一些预先定义好的方式(例如：setter 方法)接受来自于容器的资源注入。相对于IOC而言，这种表述更直接。

IOC 描述的是一种思想，而DI 是对IOC思想的具体实现. 



##### 1.3 IOC容器在Spring中的实现

* 1）在通过IOC容器读取Bean的实例之前，需要先将IOC容器本身实例化。

* Spring提供了IOC容器的两种实现方式
  * ① BeanFactory：IOC容器的基本实现，是Spring内部的基础设施，是面向Spring本身的，不是提供给开发人员使用的。
  * ② ApplicationContext：BeanFactory的子接口，提供了更多高级特性。面向Spring的使用者，几乎所有场合都使用ApplicationContext而不是底层的BeanFactory。

##### 1.4 ApplicationContext的主要实现类

* 1) ClassPathXmlApplicationContext：对应类路径下的XML格式的配置文件

* 2) FileSystemXmlApplicationContext：对应文件系统中的XML格式的配置文件

* 3) 在初始化时就创建单例的bean，也可以通过配置的方式指定创建的Bean是多实例的。

##### 1.5 ConfigurableApplicationContext

* 1) 是ApplicationContext的子接口，包含一些扩展方法

* 2) refresh()和close()让ApplicationContext具有启动、关闭和刷新上下文的能力。

##### 1.6 WebApplicationContext

专门为WEB应用而准备的，它允许从相对于WEB根目录的路径中完成初始化工作

##### 1.7 结构图（从下往上看）

![image-20200724170920625](.\imgs\spring.png)

* Core Container：核心容器
* AOP：面向切面编程
* Data Access
* Web



#### 二、HelloWorld

> 快速开发一个Spring HelloWorld程序



* 第一步：搭建Spring运行环境

  * 导入jar包

    ```
    # 1. 导入Spring自身核心JAR包：
    spring-beans-4.0.0.RELEASE.jar
    spring-context-4.0.0.RELEASE.jar
    spring-core-4.0.0.RELEASE.jar
    spring-expression-4.0.0.RELEASE.jar
    
    # 2. 导入core依赖包 
    commons-logging-1.1.1.jar
    ```

  * 创建 Spring Config 配置文件`applicationContext.xml`

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
      <!--   
  		IOC容器 
  		创建相同类不同id的bean
  	-->
      <bean id="person" class="com.beans.Person">
          <property name="name" value="JosenLi"></property>
      </bean>
  
      <bean id="person2" class="com.beans.Person">
          <property name="name" value="Rose"></property>
      </bean>
  </beans>
  ```

* 第二步：创建Bean类 - Person 

  ```java
  package com.beans;
  /**
   * @ClassName Person
   */
  public class Person {
      private String name;
      public Person() {
      }
      public Person(String name) {
          this.name = name;
      }
      @Override
      public String toString() {
          return "Person{" +
                  "name='" + name + '\'' +
                  '}';
      }
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  }
  ```
  
* 第三步：加载IOC容器，获取bean实例对象的三种方式

  ```java
      @Test
      public void testGetIOCBean(){
          // 加载IOC容器
          ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
          // 使用IOC容器，获取xml配置好的bean对象
          // 方式一：需要强转
          Person person = (Person)context.getBean("person");
          System.out.println(person);
  
          // 方式二：如果出现多个兼容类型的bean，会出现错误
          // expected single matching bean but found 2: person,person2
  //        Person p2 = context.getBean(Person.class);
  //        System.out.println(p2);
          // 方式三：常用方式
          Person p3 = context.getBean("person2", Person.class);
          Person p4 = context.getBean("person", Person.class);
          System.out.println(p3);
          System.out.println(p4);
      }
  ```

* 总结：

  * 当调用`new ClassPathXmlApplicationContext("applicationContext.xml")`加载IOC容器的时候，默认会创建xml下所有的bean实例；





#### 三、IOC容器和Bean配置

##### 3.1 DI依赖注入的三种赋值方式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--  DI依赖注入  -->
    <!-- 方式一：
				通过bean的setXxx()方法赋值   
	-->
    <bean id="setCar" class="com.beans.Car">
        <property name="brand" value="奥迪"></property>
        <property name="price" value="400000"></property>
    </bean>

    <!--
        方式二：通过构造器方式赋值
        value=传入构造器的值
        index=第几个参数
        type=传入的类型
    -->
    <bean id="consCar" class="com.beans.Car">
        <constructor-arg value="马自达" index="0" type="java.lang.String">
        </constructor-arg>
        <constructor-arg value="120000" index="1" type="java.lang.Double">
        </constructor-arg>
    </bean>
    
    <!--  使用p命名空间  -->
    <bean
            id="carByP"
            class="com.beans.Car"
            p:brand="雪佛兰"
            p:price="200000"
    >
    </bean>
</beans>
```



##### 3.2 可以使用的值

* 字面量

  * 显式的将value值赋值给property或bean；
  * 若字面值中包含特殊字符，可以使用`<![CDATA[]]>`把字面值包裹起来；

* null值

  ```xml
  <bean>
      <null/>
  </bean>
  ```

* 级联属性赋值

  ```xml
  <bean id="action" class="ref.Action">
            <property name="service" ref="service"/>
            <!-- 设置级联属性 -->
            <property name="dao.dataSource" value="DBCP"/>
  </bean>
  ```

* 引用外部已声明的bean

  ```xml
  <bean id="shop" class="beans.Shop" >
      <!-- 引用book -->
      <property name= "book" ref ="book"/>
  </bean >
  
  <bean id="book" class="beans.Book" >
      <property name= "name" value="哈利波特"/>
  </bean >
  ```

* 内部bean

  ```xml
  <bean id="shop" class="beans.Shop" >
      <property name= "book">
          <bean class= "beans.Book" >
             <property name= "name" value ="哈利波特"/>
          </bean>
      </property>
  </bean >
  ```

  

##### 3.3 属性集合

* 数组和List集合

  ```xml
  <bean id="richMan" class="beans.RichMan">
          <property name="name" value="James"></property>
          <property name="cars">
              <list>
                  <ref bean="car"></ref>
                  <ref bean="car2"></ref>
              </list>
          </property>
  </bean>
  <bean id="car" class="beans.Car">
          <property name="price" value="200000"></property>
          <property name="brand" value="五菱宏光"></property>
  </bean>
  <bean id="car2" class="beans.Car">
          <property name="price" value="150000"></property>
          <property name="brand" value="卡罗拉"></property>
  </bean>
  ```

* Map集合

  ```xml
  <bean id="richMan2" class="beans.RichMan">
     <property name="name" value="Mark"></property>
     <property name="carsMap">
        <map>
            <entry key="1" value-ref="car"></entry>
            <entry key="2" value-ref="car2"></entry>
        </map>
     </property>
  </bean>
  
  <bean id="car" class="beans.Car">
          <property name="price" value="200000"></property>
          <property name="brand" value="五菱宏光"></property>
  </bean>
  <bean id="car2" class="beans.Car">
          <property name="price" value="150000"></property>
          <property name="brand" value="卡罗拉"></property>
  </bean>
  ```

* 集合类型的bean

  * 引用util命名空间，将集合bean的配置拿到外面，供其他bean引用；

    ```xml
    	<bean id="richMan2" class="beans.RichMan">
            <property name="name" value="Mark"></property>
            <property name="carsMap" ref="utilCars"></property>
        </bean>
    
    	<!--  使用util命名空间，定义通用map集合bean  -->
        <util:map id="utilCars">
            <entry key="1" value-ref="car"></entry>
            <entry key="2" value-ref="car2"></entry>
        </util:map>
    
        <bean id="car" class="beans.Car">
            <property name="price" value="200000"></property>
            <property name="brand" value="五菱宏光"></property>
        </bean>
        <bean id="car2" class="beans.Car">
            <property name="price" value="150000"></property>
            <property name="brand" value="卡罗拉"></property>
        </bean>
    ```

* FactoryBean
  * Spring中有两种类型的bean，一种是普通bean，另一种是工厂bean；
  
  * 工厂bean跟普通bean不同，其返回的对象不是指定类的一个实例，其返回的是该工    厂bean的getObject方法所返回的对象；
  
  * 工厂bean`必须实现org.springframework.beans.factory.FactoryBean接口`；
  
    ```java
    /**
     * @ClassName MyFactoryBean
     * @Description 工厂bean
     * @Author Josen
     */
    public class MyFactoryBean implements FactoryBean<Car> {
    
        /**
         * 将创建好的bean返回给IOC容器
         * @return
         * @throws Exception
         */
        @Override
        public Car getObject() throws Exception {
            return new Car("法拉利",888888.88);
        }
    
        /**
         * 返回bean类型
         * @return
         */
        @Override
        public Class<Car> getObjectType() {
            return Car.class;
        }
    
        /**
         * 创建的bean是否是单例模式
         * @return
         */
        @Override
        public boolean isSingleton() {
            return false;
        }
    }
    ```
  
    



##### 3.4 bean的高级配置

###### ①-配置信息的继承

```xml
<!--    使用parent继承bean    -->
<bean id="father" abstract="true">
    <property name="brand" value="奥迪"></property>
    <property name="price" value="300000"></property>
</bean>

<bean id="car1" class="com.beans.Car" parent="father"></bean>
```

###### ②-bean之间的依赖

```xml
		<bean id="car1" class="com.beans.Car" parent="father"></bean>

        <!--  
			depends-on定义bean之间的依赖关系，
			如果没有配置依赖bean则无法使用当前bean  
		-->
        <bean id="man1" class="com.beans.RichMan" depends-on="car1">
            <property name="name" value="Timi"></property>
            <property name="cars">
                <list>
                    <ref bean="car1"></ref>
                    <ref bean="car1"></ref>
                </list>
            </property>
        </bean>
```

###### ③-bean的作用域【重要】

可以在IOC容器XML中通过配置`<bean>`元素的scope属性里设置bean的作用域，以决定这个bean的作用域类型；

![image-20200725122132703](.\imgs\scope.png)

```xml
	<!--  默认scope="singleton"  -->
    <bean id="car" class="com.beans.Car" scope="prototype">
        <property name="brand" value="BMW"></property>
        <property name="price" value="388888"></property>
    </bean>
```

```java
		ApplicationContext context = new ClassPathXmlApplicationContext("TestScope.xml");
		// 1. 获取两个相同car的bean
		Car car = context.getBean("car", Car.class);
        System.out.println(car);
        Car c2 = context.getBean("car", Car.class);
		// 2. 比较两个bean的地址
		// scope="singleton" ：单例，则地址相同。不会创建两个不同的实例对象
		// scope="prototype" ：原型/多例。每次getBean()会调用构造器创建新的对象
        System.out.println(car==c2);
```



###### ④-bean的生命周期

```xml
    <!--
        测试bean的生命周期
        init-method：指定Employee类中的init方法为初始化时调用的方法
        destroy-method：指定Employee类中的destroy方法为销毁时调用的方法
    -->
    <bean id="employee" class="beans.Employee" init-method="init" destroy-method="destroy">
        <property name="name" value="Jack"></property>
        <property name="age" value="22"></property>
    </bean>
    <!--  
        配置后置处理器
        MyBeanPostProcessor类实现BeanPostProcessor接口  

		postProcessBeforeInitialization：在bean的生命周期初始化方法执行之前调用
		postProcessAfterInitialization：在bean的声明周期初始化方法执行之后调用
    -->
    <bean class="bean_lifecycle.MyBeanPostProcessor"></bean>
```

**声明周期示意图**

![LifeCycle](.\imgs\LifeCycle.png)

###### ⑤-引用外部属性文件



**IOC容器配置c3p0连接池** 

> mysql8.x版本driver=com.mysql.cj.jdbc.Driver

c3p0.properties

```properties
prop.userName=root
prop.password=root
prop.url=jdbc:mysql://localhost:3306/myemployees?serverTimezone=GMT%2B8
prop.driverClass=com.mysql.cj.jdbc.Driver
```

c3p0_config.xml

```xml
 	<!-- classpath:xxx 表示属性文件位于类路径下 -->
    <context:property-placeholder location="classpath:c3p0.properties"/>
	<!-- 使用外部属性文件的值 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${prop.driverClass}"></property>
        <property name="jdbcUrl" value="${prop.url}"></property>
        <property name="user" value="${prop.userName}"></property>
        <property name="password" value="${prop.password}"></property>
        <property name="minPoolSize" value="5"></property>
        <property name="maxPoolSize" value="10"></property>
    </bean>
```

获取c3p0连接池bean

```java
public class TestDataSource {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("c3p0_config.xml");
        // 获取bean c3p0连接池对象
        ComboPooledDataSource dataSource = context.getBean("dataSource", ComboPooledDataSource.class);
        System.out.println(dataSource);
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```



##### 3.5 自动装配

> 自动装配条件：bean的属性值类型必须是引用数据类型；

```xml
    <!--
        autowire-自动装配：bean的属性值类型必须是引用数据类型
        byName：根据bean属性值名字匹配IOC容器中对应id值的bean，自动填入
        byType：根据bean属性值类型匹配IOC容器中对应类型的<bean/>
      -->
	<!-- 自动装配car属性 -->
    <bean id="emp2" class="beans.Employee" autowire="byName">
        <property name="name" value="LadyGaga"></property>
    </bean>

  	<bean id="car" class="beans.Car" scope="prototype">
        <property name="brand" value="BMW"></property>
        <property name="price" value="388888"></property>
    </bean>
```

Employee.java

```java
public class Employee {
    private String name;
    private int age;
    private Car car;
    //省略...
}
```



##### 3.6 通过注解配置bean【重要】

> 注意：使用注解配置bean的时候，需要导入spring-aop-4.0.0.RELEASE.jar包；

