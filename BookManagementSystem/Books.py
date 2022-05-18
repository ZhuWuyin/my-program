import os.path

'''
This function is used to add information, input from user, of book
to the whole book-information table
'''
def addBook(books, bookType, bookName, bookPrice):
    try :
        books[bookName]
        print("This book already exist")
        return
    except KeyError:
        books[bookName]=["Type: "+bookType, "Price: "+bookPrice]
    try :
        books[bookType].append(["Name: "+bookName, "Price: "+bookPrice])
    except KeyError:
        books[bookType]=[["Name: "+bookName, "Price: "+bookPrice]]
    try :
        books[bookPrice].append(["Type: "+bookType, "Name: "+bookName])
    except KeyError:
        books[bookPrice]=[["Type: "+bookType, "Name: "+bookName]]
    try :
        bookInfo=["Type: "+bookType, "Name: "+bookName, "Price: "+bookPrice]
        if bookInfo not in books["all"]:
            books["all"].append(bookInfo)
    except KeyError:
        books["all"]=[bookInfo]

def nameBasedDel(books, bookName):
    try :
        bookInfo=[i for i in books["all"] if bookName in i][0]
        books[bookInfo[0]].remove(bookInfo[1:])
        books[bookInfo[-1]].remove(bookInfo[:-1])
        books["all"].remove(bookInfo)
        del books[bookInfo[1]]
        print("Delete successfully!")
    except IndexError :
        print("No such book")

def typeBaseDel(books, bookType):
    try :
        deleteList=books[bookType]
    except KeyError:
        print("No such book type")
        return
    del books[bookType]
    for info in deleteList:
        bookInfo=[i for i in books["all"] if info[0] in i][0]
        books[bookInfo[-1]].remove([bookType, info[0]])
        books["all"].remove(bookInfo)
        del books[bookInfo[1]]
    print("Delete successfully!")

def priceBaseDel(books, bookPrice):
    try :
        deleteList=books[bookPrice]
    except KeyError:
        print("Input value doesn't exist")
        return
    del books[bookPrice]
    for info in deleteList:
        bookInfo=[i for i in books["all"] if info[-1] in i][0]
        books[bookInfo[0]].remove([info[-1], bookPrice])
        books["all"].remove(bookInfo)
        del books[bookInfo[1]]
    print("Delete successfully!")

'''
This function is used to find book information
If operation(user's input) is book name, it will show the information of that book
If operation is book type, it will show information of books which is that type
If operation is book price, it will show information of books which at that price
'''
def findInfo(books, operation):
    try :
        value=books[operation]
        if operation=="all":
            for i in value:
                for j in i:
                    print(j+", ", end="")
                print()
        elif not type(value[0])==list:
            print("Name: "+operation+", "+value[0]+", "+value[1])
        else :
            for i in value:
                if "Type" in i[0]:
                    print("Price: "+operation+", ", end="")
                else :
                    print("Type: "+operation+", ", end="")
                for j in i:
                    print(j+", ", end="")
                print()

    except KeyError:
        print("Input value doesn't exsit")

# Load library(all books' information) if "Books.info" file exist
# Creat a new file if "Books.info" file doesn't exist
if os.path.exists("Books.info"):
    with open("Books.info", 'r') as act:
        start=act.readline()
        if start:
            books=eval(start)
        else :
            books={}
else :
    with open("Books.info", 'w') as act:
        books={}

# Print the guidance
print("Welcome to book management system\n"+
      "Type \"add\" to add books\n"+
      "Type \"delete\" to delete books\n"+
      "Type a book type to check books' information of that type\n"+
      "Type the book name to check the information of that book\n"+
      "Type a price to check the books at that price\n"+
      "Type \"all\" to check all information\n"+
      "Type \"exit\" to quit the system\n")

while True:

    operation=input("\nInput: ")
    if operation=="exit":
        break
    
    elif operation=="add":  # To add a book into the library
        while True:
            bookType=input("\nBook Type: ")
            bookName=input("Book Name: ")
            bookPrice=input("Book Price: ")
            addBook(books, bookType, bookName, bookPrice)
            determinant=input("Do you want to continue adding?(Y/N): ")
            if determinant in ["No", "N", "n", "no"]:
                break
        with open("Books.info", 'w') as fin:
            fin.write(str(books))

    elif operation=="delete":   # To delete some information
        # Print guidance
        print("Type \"type (book type)\" to delete a book type and the books of that type\n"+
              "Type \"name (book name)\" to delete all information of a book\n"+
              "Type \"price (bookprice)\" to delete books at that price\n"+
              "Type \"delete all\" to delete all book information\n"+
              "Type \"exit\" to finish current process"+
              "Type a book type to check books' information of that type\n"+
              "Type the book name to check the information of that book\n"+
              "Type a price to check the books at that price"+
              "Type \"all\" to check all information\n")

        while True:
            deleteOperation=input("\nInput(deleting): ")
            deleteInfo=deleteOperation.split()
            if deleteOperation=="exit":
                with open("Books.info", 'w') as fin:
                    fin.write(str(books))
                break
            elif deleteInfo[0]=="type":
                typeBaseDel(books, deleteInfo[-1])
            elif deleteInfo[0]=="name":
                nameBasedDel(books, deleteInfo[-1])
            elif deleteInfo[0]=="price":
                priceBaseDel(books, deleteInfo[-1])
            elif deleteInfo[0]=="delete":
                books={}
                print("Delete successfully!")
            else :
                findInfo(books, deleteOperation)

    else :
        findInfo(books, operation)