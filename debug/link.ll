; ModuleID = 'link.bc'
source_filename = "llvm-link"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct.Edge = type { i32, i32, i32 }

@glob_n = global i32 0
@glob_m = global i32 0
@glob_ans = global i32 0
@glob_fa = global i32* null
@glob_rk = global i32* null
@glob_e = global %struct.Edge** null
@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

define void @globInit() {
entry_of_globInit:
  store i32 0, i32* @glob_ans
  store i32* null, i32** @glob_fa
  store i32* null, i32** @glob_rk
  store %struct.Edge** null, %struct.Edge*** @glob_e
  br label %exit_of_globInit

exit_of_globInit:                                 ; preds = %entry_of_globInit
  ret void
}

define void @qsort(%struct.Edge** %e, i32 %l, i32 %r) {
entry_of_qsort:
  %x.addr = alloca %struct.Edge*
  %j.addr = alloca i32
  %i.addr = alloca i32
  %r.addr = alloca i32
  %l.addr = alloca i32
  %e.addr = alloca %struct.Edge**
  store %struct.Edge** %e, %struct.Edge*** %e.addr
  store i32 %l, i32* %l.addr
  store i32 %r, i32* %r.addr
  %l.addr.load = load i32, i32* %l.addr
  %r.addr.load = load i32, i32* %r.addr
  %slt = icmp slt i32 %l.addr.load, %r.addr.load
  br i1 %slt, label %if.then, label %if.else

if.then:                                          ; preds = %entry_of_qsort
  %l.addr.load1 = load i32, i32* %l.addr
  store i32 %l.addr.load1, i32* %i.addr
  %r.addr.load1 = load i32, i32* %r.addr
  store i32 %r.addr.load1, i32* %j.addr
  %e.addr.load = load %struct.Edge**, %struct.Edge*** %e.addr
  %l.addr.load2 = load i32, i32* %l.addr
  %e.addr.load.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load, i32 %l.addr.load2
  %e.addr.load.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load.elm
  store %struct.Edge* %e.addr.load.elm.load, %struct.Edge** %x.addr
  br label %while.cond

if.else:                                          ; preds = %entry_of_qsort
  br label %if.end

if.end:                                           ; preds = %while.end, %if.else
  br label %exit_of_qsort

while.cond:                                       ; preds = %if.end2, %if.then
  %i.addr.load = load i32, i32* %i.addr
  %j.addr.load = load i32, i32* %j.addr
  %slt1 = icmp slt i32 %i.addr.load, %j.addr.load
  br i1 %slt1, label %while.body, label %while.end

while.body:                                       ; preds = %while.cond
  br label %while.cond1

while.end:                                        ; preds = %while.cond
  %e.addr.load7 = load %struct.Edge**, %struct.Edge*** %e.addr
  %i.addr.load10 = load i32, i32* %i.addr
  %e.addr.load7.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load7, i32 %i.addr.load10
  %e.addr.load7.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load7.elm
  %x.addr.load2 = load %struct.Edge*, %struct.Edge** %x.addr
  store %struct.Edge* %x.addr.load2, %struct.Edge** %e.addr.load7.elm
  %e.addr.load8 = load %struct.Edge**, %struct.Edge*** %e.addr
  %l.addr.load3 = load i32, i32* %l.addr
  %i.addr.load11 = load i32, i32* %i.addr
  %sub2 = sub i32 %i.addr.load11, 1
  call void @qsort(%struct.Edge** %e.addr.load8, i32 %l.addr.load3, i32 %sub2)
  %e.addr.load9 = load %struct.Edge**, %struct.Edge*** %e.addr
  %i.addr.load12 = load i32, i32* %i.addr
  %add2 = add i32 %i.addr.load12, 1
  %r.addr.load2 = load i32, i32* %r.addr
  call void @qsort(%struct.Edge** %e.addr.load9, i32 %add2, i32 %r.addr.load2)
  br label %if.end

while.cond1:                                      ; preds = %while.body1, %while.body
  %i.addr.load1 = load i32, i32* %i.addr
  %j.addr.load1 = load i32, i32* %j.addr
  %slt2 = icmp slt i32 %i.addr.load1, %j.addr.load1
  br i1 %slt2, label %calc.True, label %exitAnd

while.body1:                                      ; preds = %exitAnd
  %j.addr.load3 = load i32, i32* %j.addr
  %sub = sub i32 %j.addr.load3, 1
  store i32 %sub, i32* %j.addr
  br label %while.cond1

while.end1:                                       ; preds = %exitAnd
  %i.addr.load2 = load i32, i32* %i.addr
  %j.addr.load4 = load i32, i32* %j.addr
  %slt3 = icmp slt i32 %i.addr.load2, %j.addr.load4
  br i1 %slt3, label %if.then1, label %if.else1

exitAnd:                                          ; preds = %calc.True, %while.cond1
  %phi = phi i1 [ false, %while.cond1 ], [ %sge, %calc.True ]
  br i1 %phi, label %while.body1, label %while.end1

calc.True:                                        ; preds = %while.cond1
  %e.addr.load1 = load %struct.Edge**, %struct.Edge*** %e.addr
  %j.addr.load2 = load i32, i32* %j.addr
  %e.addr.load1.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load1, i32 %j.addr.load2
  %e.addr.load1.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load1.elm
  %Edge_w = getelementptr inbounds %struct.Edge, %struct.Edge* %e.addr.load1.elm.load, i32 0, i32 2
  %Edge_w.load = load i32, i32* %Edge_w
  %x.addr.load = load %struct.Edge*, %struct.Edge** %x.addr
  %Edge_w1 = getelementptr inbounds %struct.Edge, %struct.Edge* %x.addr.load, i32 0, i32 2
  %Edge_w1.load = load i32, i32* %Edge_w1
  %sge = icmp sge i32 %Edge_w.load, %Edge_w1.load
  br label %exitAnd

if.then1:                                         ; preds = %while.end1
  %e.addr.load2 = load %struct.Edge**, %struct.Edge*** %e.addr
  %i.addr.load3 = load i32, i32* %i.addr
  %e.addr.load2.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load2, i32 %i.addr.load3
  %e.addr.load2.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load2.elm
  %e.addr.load3 = load %struct.Edge**, %struct.Edge*** %e.addr
  %j.addr.load5 = load i32, i32* %j.addr
  %e.addr.load3.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load3, i32 %j.addr.load5
  %e.addr.load3.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load3.elm
  store %struct.Edge* %e.addr.load3.elm.load, %struct.Edge** %e.addr.load2.elm
  %i.addr.load4 = load i32, i32* %i.addr
  %add = add i32 %i.addr.load4, 1
  store i32 %add, i32* %i.addr
  br label %if.end1

if.else1:                                         ; preds = %while.end1
  br label %if.end1

if.end1:                                          ; preds = %if.else1, %if.then1
  br label %while.cond2

while.cond2:                                      ; preds = %while.body2, %if.end1
  %i.addr.load5 = load i32, i32* %i.addr
  %j.addr.load6 = load i32, i32* %j.addr
  %slt4 = icmp slt i32 %i.addr.load5, %j.addr.load6
  br i1 %slt4, label %calc.True1, label %exitAnd1

while.body2:                                      ; preds = %exitAnd1
  %i.addr.load7 = load i32, i32* %i.addr
  %add1 = add i32 %i.addr.load7, 1
  store i32 %add1, i32* %i.addr
  br label %while.cond2

while.end2:                                       ; preds = %exitAnd1
  %i.addr.load8 = load i32, i32* %i.addr
  %j.addr.load7 = load i32, i32* %j.addr
  %slt6 = icmp slt i32 %i.addr.load8, %j.addr.load7
  br i1 %slt6, label %if.then2, label %if.else2

exitAnd1:                                         ; preds = %calc.True1, %while.cond2
  %phi1 = phi i1 [ false, %while.cond2 ], [ %slt5, %calc.True1 ]
  br i1 %phi1, label %while.body2, label %while.end2

calc.True1:                                       ; preds = %while.cond2
  %e.addr.load4 = load %struct.Edge**, %struct.Edge*** %e.addr
  %i.addr.load6 = load i32, i32* %i.addr
  %e.addr.load4.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load4, i32 %i.addr.load6
  %e.addr.load4.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load4.elm
  %Edge_w2 = getelementptr inbounds %struct.Edge, %struct.Edge* %e.addr.load4.elm.load, i32 0, i32 2
  %Edge_w2.load = load i32, i32* %Edge_w2
  %x.addr.load1 = load %struct.Edge*, %struct.Edge** %x.addr
  %Edge_w3 = getelementptr inbounds %struct.Edge, %struct.Edge* %x.addr.load1, i32 0, i32 2
  %Edge_w3.load = load i32, i32* %Edge_w3
  %slt5 = icmp slt i32 %Edge_w2.load, %Edge_w3.load
  br label %exitAnd1

if.then2:                                         ; preds = %while.end2
  %e.addr.load5 = load %struct.Edge**, %struct.Edge*** %e.addr
  %j.addr.load8 = load i32, i32* %j.addr
  %e.addr.load5.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load5, i32 %j.addr.load8
  %e.addr.load5.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load5.elm
  %e.addr.load6 = load %struct.Edge**, %struct.Edge*** %e.addr
  %i.addr.load9 = load i32, i32* %i.addr
  %e.addr.load6.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %e.addr.load6, i32 %i.addr.load9
  %e.addr.load6.elm.load = load %struct.Edge*, %struct.Edge** %e.addr.load6.elm
  store %struct.Edge* %e.addr.load6.elm.load, %struct.Edge** %e.addr.load5.elm
  %j.addr.load9 = load i32, i32* %j.addr
  %sub1 = sub i32 %j.addr.load9, 1
  store i32 %sub1, i32* %j.addr
  br label %if.end2

if.else2:                                         ; preds = %while.end2
  br label %if.end2

if.end2:                                          ; preds = %if.else2, %if.then2
  br label %while.cond

exit_of_qsort:                                    ; preds = %if.end
  ret void
}

define void @init() {
entry_of_init:
  %i.addr1 = alloca i32
  %glob_fa.load = load i32*, i32** @glob_fa
  %glob_n.load = load i32, i32* @glob_n
  %add3 = add i32 %glob_n.load, 1
  %mul = mul i32 4, %add3
  %add4 = add i32 4, %mul
  %malloc.call = call i8* @malloc(i32 %add4)
  %bitcast = bitcast i8* %malloc.call to i32*
  store i32 %add3, i32* %bitcast
  %ahead = getelementptr inbounds i32, i32* %bitcast, i32 1
  %bitcast1 = bitcast i32* %ahead to i32*
  store i32* %bitcast1, i32** @glob_fa
  %glob_rk.load = load i32*, i32** @glob_rk
  %glob_n.load1 = load i32, i32* @glob_n
  %add5 = add i32 %glob_n.load1, 1
  %mul1 = mul i32 4, %add5
  %add6 = add i32 4, %mul1
  %malloc.call1 = call i8* @malloc(i32 %add6)
  %bitcast2 = bitcast i8* %malloc.call1 to i32*
  store i32 %add5, i32* %bitcast2
  %ahead1 = getelementptr inbounds i32, i32* %bitcast2, i32 1
  %bitcast3 = bitcast i32* %ahead1 to i32*
  store i32* %bitcast3, i32** @glob_rk
  %i.addr1.load = load i32, i32* %i.addr1
  store i32 1, i32* %i.addr1
  br label %for.cond

for.cond:                                         ; preds = %for.inc, %entry_of_init
  %i.addr1.load1 = load i32, i32* %i.addr1
  %glob_n.load2 = load i32, i32* @glob_n
  %sle = icmp sle i32 %i.addr1.load1, %glob_n.load2
  br i1 %sle, label %for.body, label %for.end

for.body:                                         ; preds = %for.cond
  %glob_fa.load1 = load i32*, i32** @glob_fa
  %i.addr1.load2 = load i32, i32* %i.addr1
  %glob_fa.load1.elm = getelementptr inbounds i32, i32* %glob_fa.load1, i32 %i.addr1.load2
  %glob_fa.load1.elm.load = load i32, i32* %glob_fa.load1.elm
  %i.addr1.load3 = load i32, i32* %i.addr1
  store i32 %i.addr1.load3, i32* %glob_fa.load1.elm
  %glob_rk.load1 = load i32*, i32** @glob_rk
  %i.addr1.load4 = load i32, i32* %i.addr1
  %glob_rk.load1.elm = getelementptr inbounds i32, i32* %glob_rk.load1, i32 %i.addr1.load4
  %glob_rk.load1.elm.load = load i32, i32* %glob_rk.load1.elm
  store i32 1, i32* %glob_rk.load1.elm
  br label %for.inc

for.inc:                                          ; preds = %for.body
  %i.addr1.load5 = load i32, i32* %i.addr1
  %add7 = add i32 %i.addr1.load5, 1
  store i32 %add7, i32* %i.addr1
  br label %for.cond

for.end:                                          ; preds = %for.cond
  br label %exit_of_init

exit_of_init:                                     ; preds = %for.end
  ret void
}

declare i8* @malloc(i32)

define i32 @find(i32 %x) {
entry_of_find:
  %x.addr1 = alloca i32
  %find.ret.addr = alloca i32
  store i32 %x, i32* %x.addr1
  %x.addr1.load = load i32, i32* %x.addr1
  %glob_fa.load2 = load i32*, i32** @glob_fa
  %x.addr1.load1 = load i32, i32* %x.addr1
  %glob_fa.load2.elm = getelementptr inbounds i32, i32* %glob_fa.load2, i32 %x.addr1.load1
  %glob_fa.load2.elm.load = load i32, i32* %glob_fa.load2.elm
  %eq = icmp eq i32 %x.addr1.load, %glob_fa.load2.elm.load
  br i1 %eq, label %if.then3, label %if.else3

if.then3:                                         ; preds = %entry_of_find
  %x.addr1.load2 = load i32, i32* %x.addr1
  store i32 %x.addr1.load2, i32* %find.ret.addr
  br label %exit_of_find

0:                                                ; No predecessors!
  br label %if.end3

if.else3:                                         ; preds = %entry_of_find
  br label %if.end3

if.end3:                                          ; preds = %if.else3, %0
  %x.addr1.load3 = load i32, i32* %x.addr1
  %glob_fa.load3 = load i32*, i32** @glob_fa
  %x.addr1.load4 = load i32, i32* %x.addr1
  %glob_fa.load3.elm = getelementptr inbounds i32, i32* %glob_fa.load3, i32 %x.addr1.load4
  %glob_fa.load3.elm.load = load i32, i32* %glob_fa.load3.elm
  %find.call = call i32 @find(i32 %glob_fa.load3.elm.load)
  store i32 %find.call, i32* %x.addr1
  %glob_fa.load4 = load i32*, i32** @glob_fa
  %x.addr1.load5 = load i32, i32* %x.addr1
  %glob_fa.load4.elm = getelementptr inbounds i32, i32* %glob_fa.load4, i32 %x.addr1.load5
  %glob_fa.load4.elm.load = load i32, i32* %glob_fa.load4.elm
  store i32 %glob_fa.load4.elm.load, i32* %find.ret.addr
  br label %exit_of_find

exit_of_find:                                     ; preds = %if.end3, %if.then3
  %find.ret.addr.load = load i32, i32* %find.ret.addr
  ret i32 %find.ret.addr.load
}

define i1 @union(i32 %x1, i32 %y) {
entry_of_union:
  %y.addr = alloca i32
  %x.addr2 = alloca i32
  %union.ret.addr = alloca i1
  store i32 %x1, i32* %x.addr2
  store i32 %y, i32* %y.addr
  %x.addr2.load = load i32, i32* %x.addr2
  %x.addr2.load1 = load i32, i32* %x.addr2
  %find.call1 = call i32 @find(i32 %x.addr2.load1)
  store i32 %find.call1, i32* %x.addr2
  %y.addr.load = load i32, i32* %y.addr
  %y.addr.load1 = load i32, i32* %y.addr
  %find.call2 = call i32 @find(i32 %y.addr.load1)
  store i32 %find.call2, i32* %y.addr
  %x.addr2.load2 = load i32, i32* %x.addr2
  %y.addr.load2 = load i32, i32* %y.addr
  %eq1 = icmp eq i32 %x.addr2.load2, %y.addr.load2
  br i1 %eq1, label %if.then4, label %if.else4

if.then4:                                         ; preds = %entry_of_union
  store i1 false, i1* %union.ret.addr
  br label %exit_of_union

0:                                                ; No predecessors!
  br label %if.end4

if.else4:                                         ; preds = %entry_of_union
  br label %if.end4

if.end4:                                          ; preds = %if.else4, %0
  %glob_rk.load2 = load i32*, i32** @glob_rk
  %x.addr2.load3 = load i32, i32* %x.addr2
  %glob_rk.load2.elm = getelementptr inbounds i32, i32* %glob_rk.load2, i32 %x.addr2.load3
  %glob_rk.load2.elm.load = load i32, i32* %glob_rk.load2.elm
  %glob_rk.load3 = load i32*, i32** @glob_rk
  %y.addr.load3 = load i32, i32* %y.addr
  %glob_rk.load3.elm = getelementptr inbounds i32, i32* %glob_rk.load3, i32 %y.addr.load3
  %glob_rk.load3.elm.load = load i32, i32* %glob_rk.load3.elm
  %sgt = icmp sgt i32 %glob_rk.load2.elm.load, %glob_rk.load3.elm.load
  br i1 %sgt, label %if.then5, label %if.else5

if.then5:                                         ; preds = %if.end4
  %glob_fa.load5 = load i32*, i32** @glob_fa
  %y.addr.load4 = load i32, i32* %y.addr
  %glob_fa.load5.elm = getelementptr inbounds i32, i32* %glob_fa.load5, i32 %y.addr.load4
  %glob_fa.load5.elm.load = load i32, i32* %glob_fa.load5.elm
  %x.addr2.load4 = load i32, i32* %x.addr2
  store i32 %x.addr2.load4, i32* %glob_fa.load5.elm
  %glob_rk.load4 = load i32*, i32** @glob_rk
  %x.addr2.load5 = load i32, i32* %x.addr2
  %glob_rk.load4.elm = getelementptr inbounds i32, i32* %glob_rk.load4, i32 %x.addr2.load5
  %glob_rk.load4.elm.load = load i32, i32* %glob_rk.load4.elm
  %glob_rk.load5 = load i32*, i32** @glob_rk
  %x.addr2.load6 = load i32, i32* %x.addr2
  %glob_rk.load5.elm = getelementptr inbounds i32, i32* %glob_rk.load5, i32 %x.addr2.load6
  %glob_rk.load5.elm.load = load i32, i32* %glob_rk.load5.elm
  %glob_rk.load6 = load i32*, i32** @glob_rk
  %y.addr.load5 = load i32, i32* %y.addr
  %glob_rk.load6.elm = getelementptr inbounds i32, i32* %glob_rk.load6, i32 %y.addr.load5
  %glob_rk.load6.elm.load = load i32, i32* %glob_rk.load6.elm
  %add8 = add i32 %glob_rk.load5.elm.load, %glob_rk.load6.elm.load
  store i32 %add8, i32* %glob_rk.load4.elm
  br label %if.end5

if.else5:                                         ; preds = %if.end4
  %glob_fa.load6 = load i32*, i32** @glob_fa
  %x.addr2.load7 = load i32, i32* %x.addr2
  %glob_fa.load6.elm = getelementptr inbounds i32, i32* %glob_fa.load6, i32 %x.addr2.load7
  %glob_fa.load6.elm.load = load i32, i32* %glob_fa.load6.elm
  %y.addr.load6 = load i32, i32* %y.addr
  store i32 %y.addr.load6, i32* %glob_fa.load6.elm
  %glob_rk.load7 = load i32*, i32** @glob_rk
  %y.addr.load7 = load i32, i32* %y.addr
  %glob_rk.load7.elm = getelementptr inbounds i32, i32* %glob_rk.load7, i32 %y.addr.load7
  %glob_rk.load7.elm.load = load i32, i32* %glob_rk.load7.elm
  %glob_rk.load8 = load i32*, i32** @glob_rk
  %y.addr.load8 = load i32, i32* %y.addr
  %glob_rk.load8.elm = getelementptr inbounds i32, i32* %glob_rk.load8, i32 %y.addr.load8
  %glob_rk.load8.elm.load = load i32, i32* %glob_rk.load8.elm
  %glob_rk.load9 = load i32*, i32** @glob_rk
  %x.addr2.load8 = load i32, i32* %x.addr2
  %glob_rk.load9.elm = getelementptr inbounds i32, i32* %glob_rk.load9, i32 %x.addr2.load8
  %glob_rk.load9.elm.load = load i32, i32* %glob_rk.load9.elm
  %add9 = add i32 %glob_rk.load8.elm.load, %glob_rk.load9.elm.load
  store i32 %add9, i32* %glob_rk.load7.elm
  br label %if.end5

if.end5:                                          ; preds = %if.else5, %if.then5
  store i1 true, i1* %union.ret.addr
  br label %exit_of_union

exit_of_union:                                    ; preds = %if.end5, %if.then4
  %union.ret.addr.load = load i1, i1* %union.ret.addr
  ret i1 %union.ret.addr.load
}

define i32 @main() {
entry_of_main:
  %ed.addr1 = alloca %struct.Edge*
  %j.addr1 = alloca i32
  %ed.addr = alloca %struct.Edge*
  %i.addr2 = alloca i32
  %main.ret.addr = alloca i32
  call void @globInit()
  store i32 0, i32* %main.ret.addr
  %glob_n.load3 = load i32, i32* @glob_n
  %getInt.call = call i32 @getInt()
  store i32 %getInt.call, i32* @glob_n
  %glob_m.load = load i32, i32* @glob_m
  %getInt.call1 = call i32 @getInt()
  store i32 %getInt.call1, i32* @glob_m
  %glob_e.load = load %struct.Edge**, %struct.Edge*** @glob_e
  %glob_m.load1 = load i32, i32* @glob_m
  %mul2 = mul i32 8, %glob_m.load1
  %add10 = add i32 4, %mul2
  %malloc.call2 = call i8* @malloc(i32 %add10)
  %bitcast4 = bitcast i8* %malloc.call2 to i32*
  store i32 %glob_m.load1, i32* %bitcast4
  %ahead2 = getelementptr inbounds i32, i32* %bitcast4, i32 1
  %bitcast5 = bitcast i32* %ahead2 to %struct.Edge**
  store %struct.Edge** %bitcast5, %struct.Edge*** @glob_e
  %i.addr2.load = load i32, i32* %i.addr2
  store i32 0, i32* %i.addr2
  br label %for.cond1

for.cond1:                                        ; preds = %for.inc1, %entry_of_main
  %i.addr2.load1 = load i32, i32* %i.addr2
  %glob_m.load2 = load i32, i32* @glob_m
  %slt7 = icmp slt i32 %i.addr2.load1, %glob_m.load2
  br i1 %slt7, label %for.body1, label %for.end1

for.body1:                                        ; preds = %for.cond1
  %malloc.call3 = call i8* @malloc(i32 12)
  %bitcast6 = bitcast i8* %malloc.call3 to %struct.Edge*
  store %struct.Edge* %bitcast6, %struct.Edge** %ed.addr
  %ed.addr.load = load %struct.Edge*, %struct.Edge** %ed.addr
  %Edge_x = getelementptr inbounds %struct.Edge, %struct.Edge* %ed.addr.load, i32 0, i32 0
  %Edge_x.load = load i32, i32* %Edge_x
  %getInt.call2 = call i32 @getInt()
  store i32 %getInt.call2, i32* %Edge_x
  %ed.addr.load1 = load %struct.Edge*, %struct.Edge** %ed.addr
  %Edge_y = getelementptr inbounds %struct.Edge, %struct.Edge* %ed.addr.load1, i32 0, i32 1
  %Edge_y.load = load i32, i32* %Edge_y
  %getInt.call3 = call i32 @getInt()
  store i32 %getInt.call3, i32* %Edge_y
  %ed.addr.load2 = load %struct.Edge*, %struct.Edge** %ed.addr
  %Edge_w4 = getelementptr inbounds %struct.Edge, %struct.Edge* %ed.addr.load2, i32 0, i32 2
  %Edge_w4.load = load i32, i32* %Edge_w4
  %getInt.call4 = call i32 @getInt()
  store i32 %getInt.call4, i32* %Edge_w4
  %glob_e.load1 = load %struct.Edge**, %struct.Edge*** @glob_e
  %i.addr2.load2 = load i32, i32* %i.addr2
  %glob_e.load1.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %glob_e.load1, i32 %i.addr2.load2
  %glob_e.load1.elm.load = load %struct.Edge*, %struct.Edge** %glob_e.load1.elm
  %ed.addr.load3 = load %struct.Edge*, %struct.Edge** %ed.addr
  store %struct.Edge* %ed.addr.load3, %struct.Edge** %glob_e.load1.elm
  br label %for.inc1

for.inc1:                                         ; preds = %for.body1
  %i.addr2.load3 = load i32, i32* %i.addr2
  %add11 = add i32 %i.addr2.load3, 1
  store i32 %add11, i32* %i.addr2
  br label %for.cond1

for.end1:                                         ; preds = %for.cond1
  %glob_e.load2 = load %struct.Edge**, %struct.Edge*** @glob_e
  %glob_m.load3 = load i32, i32* @glob_m
  %sub3 = sub i32 %glob_m.load3, 1
  call void @qsort(%struct.Edge** %glob_e.load2, i32 0, i32 %sub3)
  call void @init()
  %i.addr2.load4 = load i32, i32* %i.addr2
  store i32 0, i32* %i.addr2
  store i32 0, i32* %j.addr1
  br label %while.cond3

while.cond3:                                      ; preds = %if.end7, %for.end1
  %i.addr2.load5 = load i32, i32* %i.addr2
  %glob_n.load4 = load i32, i32* @glob_n
  %sub4 = sub i32 %glob_n.load4, 1
  %slt8 = icmp slt i32 %i.addr2.load5, %sub4
  br i1 %slt8, label %while.body3, label %while.end3

while.body3:                                      ; preds = %while.cond3
  %j.addr1.load = load i32, i32* %j.addr1
  %glob_m.load4 = load i32, i32* @glob_m
  %sge1 = icmp sge i32 %j.addr1.load, %glob_m.load4
  br i1 %sge1, label %if.then6, label %if.else6

while.end3:                                       ; preds = %while.cond3
  %glob_rk.load10 = load i32*, i32** @glob_rk
  %find.call3 = call i32 @find(i32 1)
  %glob_rk.load10.elm = getelementptr inbounds i32, i32* %glob_rk.load10, i32 %find.call3
  %glob_rk.load10.elm.load = load i32, i32* %glob_rk.load10.elm
  %glob_n.load5 = load i32, i32* @glob_n
  %eq2 = icmp eq i32 %glob_rk.load10.elm.load, %glob_n.load5
  br i1 %eq2, label %if.then8, label %if.else8

if.then6:                                         ; preds = %while.body3
  %sub5 = sub i32 0, 1
  call void @printInt(i32 %sub5)
  store i32 0, i32* %main.ret.addr
  br label %exit_of_main

0:                                                ; No predecessors!
  br label %if.end6

if.else6:                                         ; preds = %while.body3
  br label %if.end6

if.end6:                                          ; preds = %if.else6, %0
  %glob_e.load3 = load %struct.Edge**, %struct.Edge*** @glob_e
  %j.addr1.load1 = load i32, i32* %j.addr1
  %glob_e.load3.elm = getelementptr inbounds %struct.Edge*, %struct.Edge** %glob_e.load3, i32 %j.addr1.load1
  %glob_e.load3.elm.load = load %struct.Edge*, %struct.Edge** %glob_e.load3.elm
  store %struct.Edge* %glob_e.load3.elm.load, %struct.Edge** %ed.addr1
  %j.addr1.load2 = load i32, i32* %j.addr1
  %add12 = add i32 %j.addr1.load2, 1
  store i32 %add12, i32* %j.addr1
  %ed.addr1.load = load %struct.Edge*, %struct.Edge** %ed.addr1
  %Edge_x1 = getelementptr inbounds %struct.Edge, %struct.Edge* %ed.addr1.load, i32 0, i32 0
  %Edge_x1.load = load i32, i32* %Edge_x1
  %ed.addr1.load1 = load %struct.Edge*, %struct.Edge** %ed.addr1
  %Edge_y1 = getelementptr inbounds %struct.Edge, %struct.Edge* %ed.addr1.load1, i32 0, i32 1
  %Edge_y1.load = load i32, i32* %Edge_y1
  %union.call = call i1 @union(i32 %Edge_x1.load, i32 %Edge_y1.load)
  br i1 %union.call, label %if.then7, label %if.else7

if.then7:                                         ; preds = %if.end6
  %i.addr2.load6 = load i32, i32* %i.addr2
  %add13 = add i32 %i.addr2.load6, 1
  store i32 %add13, i32* %i.addr2
  %glob_ans.load = load i32, i32* @glob_ans
  %glob_ans.load1 = load i32, i32* @glob_ans
  %ed.addr1.load2 = load %struct.Edge*, %struct.Edge** %ed.addr1
  %Edge_w5 = getelementptr inbounds %struct.Edge, %struct.Edge* %ed.addr1.load2, i32 0, i32 2
  %Edge_w5.load = load i32, i32* %Edge_w5
  %add14 = add i32 %glob_ans.load1, %Edge_w5.load
  store i32 %add14, i32* @glob_ans
  br label %if.end7

if.else7:                                         ; preds = %if.end6
  br label %if.end7

if.end7:                                          ; preds = %if.else7, %if.then7
  br label %while.cond3

if.then8:                                         ; preds = %while.end3
  %glob_ans.load2 = load i32, i32* @glob_ans
  call void @printInt(i32 %glob_ans.load2)
  br label %if.end8

if.else8:                                         ; preds = %while.end3
  %sub6 = sub i32 0, 1
  call void @printInt(i32 %sub6)
  br label %if.end8

if.end8:                                          ; preds = %if.else8, %if.then8
  store i32 0, i32* %main.ret.addr
  br label %exit_of_main

exit_of_main:                                     ; preds = %if.end8, %if.then6
  %main.ret.addr.load = load i32, i32* %main.ret.addr
  ret i32 %main.ret.addr.load
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @_toy_malloc(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call noalias i8* bitcast (i8* (i32)* @malloc to i8* (i64)*)(i64 %4) #5
  ret i8* %5
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  ret void
}

declare dso_local i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @println(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printlnInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @getString() #0 {
  %1 = alloca i8*, align 8
  %2 = call noalias i8* bitcast (i8* (i32)* @malloc to i8* (i64)*)(i64 1024) #5
  store i8* %2, i8** %1, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  %5 = load i8*, i8** %1, align 8
  ret i8* %5
}

declare dso_local i32 @__isoc99_scanf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @toString(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8*, align 8
  store i32 %0, i32* %2, align 4
  %4 = call noalias i8* bitcast (i8* (i32)* @malloc to i8* (i64)*)(i64 13) #5
  store i8* %4, i8** %3, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %2, align 4
  %7 = call i32 (i8*, i8*, ...) @sprintf(i8* %5, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %6) #5
  %8 = load i8*, i8** %3, align 8
  ret i8* %8
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @_str_add(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %6 = load i8*, i8** %3, align 8
  %7 = call i64 @strlen(i8* %6) #6
  %8 = load i8*, i8** %4, align 8
  %9 = call i64 @strlen(i8* %8) #6
  %10 = add i64 %7, %9
  %11 = add i64 %10, 1
  %12 = mul i64 1, %11
  %13 = call noalias i8* bitcast (i8* (i32)* @malloc to i8* (i64)*)(i64 %12) #5
  store i8* %13, i8** %5, align 8
  %14 = load i8*, i8** %5, align 8
  %15 = load i8*, i8** %3, align 8
  %16 = call i8* @strcpy(i8* %14, i8* %15) #5
  %17 = load i8*, i8** %5, align 8
  %18 = load i8*, i8** %4, align 8
  %19 = call i8* @strcat(i8* %17, i8* %18) #5
  %20 = load i8*, i8** %5, align 8
  ret i8* %20
}

; Function Attrs: nounwind readonly
declare dso_local i64 @strlen(i8*) #3

; Function Attrs: nounwind
declare dso_local i8* @strcpy(i8*, i8*) #2

; Function Attrs: nounwind
declare dso_local i8* @strcat(i8*, i8*) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @_str_eq(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp ne i32 %7, 0
  %9 = xor i1 %8, true
  ret i1 %9
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strcmp(i8*, i8*) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @_str_neq(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp ne i32 %7, 0
  ret i1 %8
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @_str_slt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp slt i32 %7, 0
  ret i1 %8
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @_str_sle(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sle i32 %7, 0
  ret i1 %8
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @_str_sgt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sgt i32 %7, 0
  ret i1 %8
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i1 @_str_sge(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sge i32 %7, 0
  ret i1 %8
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @_str_length(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i64 @strlen(i8* %3) #6
  %5 = trunc i64 %4 to i32
  ret i32 %5
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @_str_substring(i8* %0, i32 %1, i32 %2) #0 {
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i8*, align 8
  store i8* %0, i8** %4, align 8
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %8 = load i32, i32* %6, align 4
  %9 = load i32, i32* %5, align 4
  %10 = sub nsw i32 %8, %9
  %11 = add nsw i32 %10, 1
  %12 = sext i32 %11 to i64
  %13 = mul i64 1, %12
  %14 = call noalias i8* bitcast (i8* (i32)* @malloc to i8* (i64)*)(i64 %13) #5
  store i8* %14, i8** %7, align 8
  %15 = load i8*, i8** %7, align 8
  %16 = load i8*, i8** %4, align 8
  %17 = load i32, i32* %5, align 4
  %18 = sext i32 %17 to i64
  %19 = getelementptr inbounds i8, i8* %16, i64 %18
  %20 = load i32, i32* %6, align 4
  %21 = load i32, i32* %5, align 4
  %22 = sub nsw i32 %20, %21
  %23 = sext i32 %22 to i64
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %15, i8* align 1 %19, i64 %23, i1 false)
  %24 = load i8*, i8** %7, align 8
  ret i8* %24
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #4

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @_str_parseInt(i8* %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  store i8* %0, i8** %2, align 8
  %4 = load i8*, i8** %2, align 8
  %5 = call i32 (i8*, i8*, ...) @__isoc99_sscanf(i8* %4, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %3) #5
  %6 = load i32, i32* %3, align 4
  ret i32 %6
}

; Function Attrs: nounwind
declare dso_local i32 @__isoc99_sscanf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @_str_ord(i8* %0, i32 %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %4, align 4
  %7 = sext i32 %6 to i64
  %8 = getelementptr inbounds i8, i8* %5, i64 %7
  %9 = load i8, i8* %8, align 1
  %10 = sext i8 %9 to i32
  ret i32 %10
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { argmemonly nounwind willreturn }
attributes #5 = { nounwind }
attributes #6 = { nounwind readonly }

!llvm.ident = !{!0}
!llvm.module.flags = !{!1}

!0 = !{!"clang version 10.0.0-4ubuntu1 "}
!1 = !{i32 1, !"wchar_size", i32 4}
