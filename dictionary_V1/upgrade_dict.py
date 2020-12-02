with open('./单词本.txt','r',encoding='utf-8') as act:
    version_num=act.readline()
    words_table=act.readlines()


words_dict1={}
words_dict2={}
for i in words_table:

    word=[]                 #英文单词
    element=''
    for a in i:                 #判断字符是否是英文字母或空格
        if ord(a)<=122 and ord(a)>=65 or a==' ':                    #字符是英文字母或空格
            element+=a
        else :
            word.append(element)
            break

    chinese=[]                  #中文释义
    element=''
    num1=0
    for a in i:                 #判断字符是否属于中文和数字
        if ord(a)>=11904 and ord(a)<=40912 and ord(a)!=12290 or ord(a)>=48 and ord(a)<=57:                    #字符属于中文或数字
            num1+=1
            element+=a
        else :                  #字符不属于中文或数字
            if num1>0:
                chinese.append(element)
                element=''
                num1=0
    chinese.append(element)
    try :                   #去除列表中的空字符串
        chinese.remove('')
    except ValueError:                  #如果遇到没有空字符串的列表就跳过
        pass

    if '1011' not in chinese:
        chinese.append('1011')

    for a in chinese:
        try :
            if words_dict1[a] and word[0] not in words_dict1['1011']:
                words_dict1[a]=words_dict1[a]+[word[0]]
        except KeyError:
            words_dict1[a]=[word[0]]

    chinese_element=';'.join(chinese)
    try :
        if words_dict2[word[0]]:
            words_dict2_chack=words_dict2[word[0]].split(';')
            for z in chinese:
                if z not in words_dict2_chack:
                    words_dict2[word[0]]=words_dict2[word[0]]+';'+z
    except KeyError:
        words_dict2[word[0]]=chinese_element

with open('./upgrade_file.in','w',encoding='utf-8') as fin:
    fin.write(version_num+str(words_dict1)+'\n'+str(words_dict2))
