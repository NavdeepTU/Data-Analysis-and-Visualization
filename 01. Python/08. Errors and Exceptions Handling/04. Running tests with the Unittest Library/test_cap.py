# When writing test functions its best to go from simple to complex as each function is going to run in order
import unittest
import cap

class TestCap(unittest.TestCase):
    
    '''
    This class has the test functions that we need to run
    '''
    
    def test_one_word(self):
        text = 'python'
        result = cap.cap_text(text)
        expected_result = 'Python'
        self.assertEqual(result, expected_result)
        
    def test_multiple_words(self):
        text = 'monty python'
        result = cap.cap_text(text)
        self.assertEqual(result, 'Monty Python')
        
if __name__ == '__main__':
    unittest.main()



(base) C:\Users\HP\Desktop>python test_cap.py
..
----------------------------------------------------------------------
Ran 2 tests in 0.000s

OK