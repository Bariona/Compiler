import os
import time

#  export CLASSPATH="/mnt/d/Program/Java/antlr4/antlr-4.11.1-complete.jar:$CLASSPATH"

# export PATH="/usr/local/opt/bin:$PATH"

def cleanup ():
    os.system("cd ../src && (find -name '*.class' | xargs rm) && cd ../debug")

if __name__ == '__main__':
	judge_list = open("../Compiler-2021-testcases/codegen/judgelist.txt").readlines()

	cnt = 0

	cleanup()

	fail_collect = list()


	for judge in judge_list:
	    cnt += 1

	    code_file = judge.replace("\n", "").replace("./", "../Compiler-2021-testcases/codegen/")

	    print(code_file)
	    print("cp {code_file} test.mx".format(code_file=code_file))
	    input_file = "input.txt"
	    output_file = "output.txt"
	    std_file = "std.txt"

	    input_fp = open(input_file, "w")
	    output_fp = open(output_file, "w")
	    std_fp = open(std_file, "w")

	    fp = open(code_file)
	    lines = fp.readlines()

	    input_start = False
	    std_start = False

	    for i in range(len(lines)):
	        if lines[i].find("= output =") > 0:
	            std_start = True
	        elif lines[i].find("= input =") > 0:
	            input_start = True
	        elif lines[i].find("= end =") > 0:
	            input_start = False
	            std_start = False
	        elif input_start:
	            input_fp.write(lines[i])
	        elif std_start:
	            std_fp.write(lines[i])

	    input_fp.close()
	    output_fp.close()
	    std_fp.close()

	    print("\033[34m Loading finish. Start to run Codegen.")

	    os.system("cd ../src && javac Compiler.java &&  cp {code_file} test.mx && java Compiler < test.mx > test.s && cd ../debug".format(code_file=code_file))

	    os.system("cp ../testspace/test.s test.s && ./test-codegen.sh < {input_file} > {output_file}".format(input_file=input_file,output_file=output_file))


	    wrap = os.popen(
	        "diff {file1} {file2} -w -B".format(file1=output_file, file2=std_file))
	    info = wrap.readlines()

	    if len(info) == 0:
	        print("\033[32m[Success] [test]: in {testpoint} \033[0m".format(
	            testpoint=code_file + ", point " + str(cnt)))
	    else:
	        print("\033[31m[Failed] [test]: in {testpoint} \033[0m".format(
	            testpoint=code_file + ", point " + str(cnt)))
	        # print("[info]: ", info)
	        fail_collect.append(code_file)

	    time.sleep(1)

	print(fail_collect)

	cleanup()
