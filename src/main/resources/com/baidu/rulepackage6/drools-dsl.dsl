[keyword]规则名=rule
[keyword]结束=end

[when][]小于或等于=<=
[when][]等于===
[when][]大于或等于=>=
[when][]小于=<
[when][]大于=>

[when][]- {field:\w*} ={field}
[when][]姓名=name
[when][]年龄=age
[when][]性别=gender
[when]满足条件的员工=$staff:Staff()
[then]输出员工姓名=System.out.println($staff.getName());