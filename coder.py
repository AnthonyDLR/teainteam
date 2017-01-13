from random import randrange
def encode_help(password, rounds):
    trans_pass = password
    for q in (range(rounds)):
        a_map = {}
        template = []
        for i in range(62):
            template.append(i)
        alphabet = 'aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789'
        new_temp = 'fp3qKD5TmntvxUuAFMZo0zCdHSrh2c467eksjwOV9gYNPJaIGBEQ1yb8iWLlRX'
        for i in range(len(alphabet)):
            a_map[alphabet[i]] = template[i]
        encoded_password = ''
        for a in trans_pass:
            encoded_password += new_temp[a_map[a]]
        trans_pass = encoded_password
    num_temp = list('&*)!(@$^#%')
    start = randrange(len(password)//2)
    for a in str(rounds):
        encoded_password = encoded_password[:start] + num_temp[int(a)] + encoded_password[start:]
        if start > len(encoded_password) - 4:
            start =  len(encoded_password) - 1
        else:
            start = randrange(start + 1, range(start + 2, len(password))[len(range(start + 2, len(password)))//2] + 1)
    return encoded_password

def decode(encode):
    trans_pass = ''
    temp_rounds = ''
    for a in encode:
        if(not a in list('&*)!(@$^#%')):
            trans_pass += a
        else:
            temp_rounds += a
    num_list = list('&*)!(@$^#%')
    rounds = ''
    for a in temp_rounds:
        rounds += str('&*)!(@$^#%'.find(a))
        
    for q in (range(int(rounds))):
        a_map = {}
        template = []
        for i in range(62):
            template.append(i)
        new_temp = 'aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789'
        alphabet = 'fp3qKD5TmntvxUuAFMZo0zCdHSrh2c467eksjwOV9gYNPJaIGBEQ1yb8iWLlRX'
        for i in range(len(alphabet)):
            a_map[alphabet[i]] = template[i]
        encoded_password = ''
        for a in trans_pass:
            encoded_password += new_temp[a_map[a]]
        trans_pass = encoded_password
    num_temp = list('&*)!(@$^#%')

    return encoded_password

class Decode_Error(Exception):
    pass
        
def encode(password, rounds):
    for q in (range(5)):
        try:
            new_pass = encode_help(password, rounds)
            if(decode(new_pass) != password):
                return "Password doesn't work"
            else:
                return new_pass
        except:
            pass
    print("Number too high")


            
