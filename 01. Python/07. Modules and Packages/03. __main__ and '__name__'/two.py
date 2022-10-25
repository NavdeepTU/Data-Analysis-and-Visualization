# two.py

import one

print("TOP LEVEL IN TWO.PY")

one.func()

if __name__ == "__main__":
    print("TWO.PY is being run directly")
else:
    print("TWO.PY has been imported!")
    
    

# (base) C:\Users\HP\Desktop>python two.py
# TOP LEVEL IN ONE.PY
# ONE.PY has been imported!
# TOP LEVEL IN TWO.PY
# FUNC() IN ONE.PY
# TWO.PY is being run directly