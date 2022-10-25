# one.py

# An often confusing part of Python is a mysterious line of code:
#       if __name__ == "__main__":

# Sometimes when you are importing from a module, you would like to know 
# whether a modules function is being used as an import, or if you are
# using the original .py file of that module.

def func():
    print("FUNC() IN ONE.PY")
    
print("TOP LEVEL IN ONE.PY")

if __name__ == "__main__":
    print("ONE.PY is being run directly!")
else:
    print("ONE.PY has been imported!")
    
    
# (base) C:\Users\HP\Desktop>python one.py
# TOP LEVEL IN ONE.PY
# ONE.PY is being run directly!