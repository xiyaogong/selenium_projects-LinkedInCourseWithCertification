-----
html element
-----
     ID <button id="login_btn">
     Name <button name="login_btn">
     class Name <button class="btn"> classes added for styling with CSS, change frequently
     Tag Name <button> by html tag, no attribute, lots of them, when there are multiple elements of the same type or no unique identifiers
     Link Text <a href="/">Click me</a> a for anchor, use text than link. link changed often
     CSS Selector <input type="password">. not only for input, input[type='password'] based on CSS properties(i think is with attribute)
     Xpath //input[@type='password']
     ############ html tags###
     button  rich in styles
     a
     div
     ul li
     table
     span
     input
     label
     select
     option
     textarea
     body
     header
     header
     footer
     form
     iframe
     img
     <label for="username">用户名</label>
     ###### input types ###
     input:  text, email, password, tel, number, search, url, file, hidden
             button(simple only show value), reset, submit, radio, checkbox, range, image, color, month
             date, time, week
     ####### choose right locator
     1.use id whenever possible
     2.use name when no id
     3.use class when no id or name
     4.use CSS selector and/or Xpath for advanced locators.(has attribute at least, use the tagName+ attribute to search)
     5.use tag name to locate multiple elements, or when no unique identifiers(attribute) are available.
     6.use link text for anchor elements with no other attributes.
     5 and 6 is the similar, no attribute


-----
css selector 常用语法
用来选择元素节点的 它定义的是如何给网页上的元素应用样式。
CSS Selector 的设计初衷是“定位元素”，而不是定位样式
-----

-----

 # XPath 常用语法与示例

-----

 ## 1. 绝对路径 vs 相对路径

 * **绝对路径**：从根节点开始，路径以 `/` 开头
   例：`/html/body/div[1]/h1`

 * **相对路径**：从当前节点开始，路径以 `//` 开头（查找所有匹配）
   例：`//div[@id='main']`

 ---

 ## 2. 选择节点

 * `//tag`：选择所有指定的标签节点
   例：`//input`（页面所有 input 元素）

 * `//tag[@attr='value']`：选择指定属性等于某值的节点
   例：`//input[@name='username']`

 * `//tag[contains(@attr, 'value')]`：属性包含某字符串
   例：`//div[contains(@class, 'active')]`

 * `//tag[text()='some text']`：元素文本完全匹配
   例：`//button[text()='提交']`

 * `//tag[contains(text(), '部分文本')]`：元素文本部分匹配
   例：`//p[contains(text(), '欢迎')]`

 ---

 ## 3. 逻辑运算符

 * `and`
   例：`//input[@type='submit' and @name='save']`

 * `or`
   例：`//input[@type='submit' or @type='button']`

 ---

 ## 4. 层级关系

 * `/`：子节点
   例：`//div[@id='main']/button`（id=main 的 div 下的 button）

 * `//`：任意后代节点
   例：`//div//button`（div 的所有后代中的 button）

 * `..`：父节点
   例：`//button/..`（button 的父元素）

 ---

 ## 5. 位置选择

 * 选择第一个匹配：`(//tag)[1]`
 * 选择第 n 个匹配：`(//tag)[n]`，索引从 1 开始

 例：

 ```xpath
 (//input[@type='text'])[2]  // 第二个文本输入框
 ```

 ---

 ## 6. 常用示例总结

 | XPath 示例                         | 含义                       |
 | -------------------------------- | ------------------------ |
 | `//input[@id='username']`        | id 为 username 的 input 元素 |
 | `//a[contains(text(), '登录')]`    | 文本包含“登录”的链接              |
 | `//button[@type='submit']`       | type 属性为 submit 的按钮      |
 | `//div[@class='menu']//a`        | class 是 menu 的 div 下所有链接 |
 | `(//input[@type='checkbox'])[3]` | 第三个复选框                   |

 ---

-----
css selector vs xpath
-----


特点	CSS Selector	XPath
是否标准	CSS 标准	XML 标准
使用难度	简单	更灵活但稍复杂
是否支持向上查找	❌ 不支持父节点查找	✅ 支持父节点查找（..）
跨层级查找	✅ 支持（div li）	✅ 支持（//div//li）
Selenium 支持	✅	✅
浏览器调试支持	✅ DevTools 支持	❌ 只能在 console 用 $x() 测试