# Unittest allows you to write your own test program and the goal is to send a specific set of data to your program
# analyze the return results and see if it gives you the expected results.

# We will create 2 scripts. One will be a simple script that capitalizes text and the another script will be the 
# actual test script for this.

def cap_text(text):
    '''
    Input a string
    Output the capitalized string
    '''
    return text.title() # will capitalize first letter of every single word in text