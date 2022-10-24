from mymodule import my_func

my_func()


# (base) C:\Users\HP>cd Desktop

# (base) C:\Users\HP\Desktop>python myprogram.py
# Hey I am in my mymodule.py

from MyMainPackage import some_main_script
from MyMainPackage.SubPackage import mysubscript

some_main_script.report_main()

mysubscript.sub_report()


# (base) C:\Users\HP\Desktop>python myprogram.py
# Hey I am in my mymodule.py
# Hey I am in some_main_script in main package.
# Hey I am function inside mysubscript