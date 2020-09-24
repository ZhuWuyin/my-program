import sys,os

with open(os.getcwd()+'/upgrade_file.in','r',encoding='utf-8') as act:
    version_num=act.readline()
    words_dict1=eval(act.readline())
    words_dict2=eval(act.readline())

with open(os.getcwd()+'/单词本.txt','r',encoding='utf-8') as chack:
    version_num_chack=chack.readline()
if version_num!=version_num_chack:
    print('有新版本，请及时更新\n')

words_dict2_1=str(words_dict2)

while True:                 #输入需要查找的单词或释义或要执行的操作（如退出程序），寻找对应的单词或释义并将他们一起输出（或执行需要执行的操作）

    search=input()
    search.lower()                  #将所输入的字符变为小写
    if search=='-break' or search=='结束' or search=='-done':
        print(search.upper())
        sys.exit(0)

    print()

    if search=='+':
        while True:
            condition=input('添加或更改单词释义请输入 —— Chinese\n添加单词和单词释义请输入 —— Word\n若想结束修改请输入 —— “-Break” 或者 “-Done”\n').lower()

            if condition=='chinese':
                try :
                    incomplete=input('需要添加释义的单词： ')
                    words_dict2[incomplete]
                    complete=input('需要添加的释义（将各个释义间用空格隔开）： ')
                    complete=complete.split(' ')
                    for i in complete:
                        if i not in words_dict2[incomplete]:
                            words_dict2[incomplete]=words_dict2[incomplete]+';'+';'.join(complete)
                    words_dict1_interm=words_dict2[incomplete].split(';')
                    for i in words_dict1_interm:
                        try :
                            if incomplete not in words_dict1[i]:
                                words_dict1[i].append(incomplete)
                        except KeyError:
                            words_dict1[i]=[incomplete]
                except KeyError:
                    print('None')
                    judged=input('是否添加该词语 —— 是请输入“Yes” 否请输入“No”\n').lower()
                    if judged=='yes':
                        condition='word'
                    else :
                        print()
                        pass

            if condition=='word':
                complete_word=input('需要添加的单词： ')
                complete_chinese=input('该单词的释义（将各个释义间用空格隔开）： ')
                complete_chinese_str=''
                num1=0
                for a in complete_chinese:
                    if ord(a)>=11904 and ord(a)<=40912 and ord(a)!=12290 or ord(a)>=48 and ord(a)<=57:
                        num1+=1
                        complete_chinese_str+=a
                    else :
                        if num1>0:
                            complete_chinese_str+=';'
                            num1=0
                complete_chinese_list=complete_chinese_str.split(';')
                words_dict2[complete_word]=complete_chinese_str

                for i in complete_chinese_list:
                    try :
                        words_dict1[i].append(complete_word)
                    except KeyError:
                        words_dict1[i]=[complete_word]

            elif condition=='-break' or condition=='-done':
                if str(words_dict2)!=words_dict2_1:
                    words_table_interm=list(words_dict2.items())
                    words_table=[]
                    for i in words_table_interm:
                        for a in i:
                            words_table.append(a)
                    element_complete=''
                    with open(os.getcwd()+'/单词本.txt','w',encoding='utf-8') as add:
                        for i in range(0,len(words_table),2):
                            element_word=words_table[i]
                            element_chinese=words_table[i+1]
                            element_complete+=element_word+': '+element_chinese+'\n'
                        element_complete=element_complete[0:-1]
                        add.write(version_num_chack+element_complete)
                    with open(os.getcwd()+'/upgrade_file.in','w',encoding='utf-8') as add:
                        add.write(version_num+str(words_dict1)+'\n'+str(words_dict2))
                else :
                    pass
                print()
                break
    else :
        num=0
        try :
            for i in words_dict1[search]:
                print(i,':',words_dict2[i])
                num+=1
        except KeyError:
            try :
                print(search,':',words_dict2[search])
                num+=1
            except KeyError:
                print('None')
        print()
        if num==len(words_dict2):
            print(num)
            print()