目录结构：

* DP（依赖倒转）
* ISP（接口隔离原则）
* Liskov（里式替换）



#### 一、设计模式概述

特点：

* 代码重用性
* 可读性
* 可扩展性
* 可靠性（在原有基础上新增功能后，对原来的功能没有影响）
* 低耦合、高内聚



##### 七大原则

* 单一职责原则：
  * 降低类的复杂度，一个类只负责一项职责
  * 提高类的可读性，可维护性
  * 降低变更引起的风险
  * 特殊情况：
    * 逻辑足够简单，可以违反单一职责；
    * 类中的方法数量足够少，可以在方法级别保持单一职责；
* 接口隔离原则（Interface isolation principle）：
  * 一个类对另一个类的依赖应该建立在最小的接口上；
* 依赖倒转（倒置）原则（Dependency principle）：
  * 依赖关系传递的三种方式：
    * 接口传递
    * 构造方法传递
    * setter方式传递
  * 高层模块不应该依赖底层模块，二者都应该依赖其抽象；
  * 抽象不应该依赖细节，细节应该依赖抽象；
  * 依赖倒转（倒置）的中心思想是`面向接口编程；`
  * 变量的声明类型尽量是抽象类或接口；
  * 继承时遵循里式替换原则；
  * 概述：相对于细节的多变性，抽象的东西要稳定得多。以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。（java中抽象指`接口或抽象类，细节就是实现类`）；
* 里式替换原则：
  * 尽量不要在子类中重写父类中的方法；
  * 概述：继承实际上让两个类耦合性增强了，在适当的情况下，可以通过聚合、组合、依赖来解决问题；
  * 问题描述：子类中过多重写父类的方法，导致使用时出现方法调用错误的问题；
* △开闭原则（Open Closed Principle）：
  * OCP原则：对扩展开放（提供方），对修改关闭（使用方）
  * 当提供方发生改变的时候，使用方是不需要改变的（类似依赖倒转）；
* 迪米特法则（Demeter Principle）：
  * 迪米特法则又叫最少知道原则，即`一个类对自己依赖的类知道的越少越好`；
  * 只与直接的朋友通信；
  * 直接的朋友：
    * 只要两个对象之间有耦合`（如：依赖、关联、组合、聚合等...）`关系，这两个对象就是朋友关系；
    * 出现在成员变量、方法参数、方法返回值中的类为直接的朋友；
    * 出现在局部变量中的类不是直接的朋友（陌生的类最好不要以局部变量的形式出现在类的内部）；
  * 迪米特法则核心是降低类之间的耦合；
* 合成复用原则
  * 尽量使用合成或聚合的方式，而不是使用继承；
  * 找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起；
  * 针对接口编程，而不是针对实现编程；
  * 为了交互对象之间的松耦合设计而努力；



#### 二、UML类图

