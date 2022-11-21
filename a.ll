; ModuleID = 'a.cpp'
source_filename = "a.cpp"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct.b = type { i32, i8, %struct.a }
%struct.a = type { i32 }

@x = dso_local global i32 0, align 4
@y = dso_local global i32 0, align 4
@zzz = dso_local global i32 0, align 4

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @_Z4testiiPib(i32 %0, i32 %1, i32* %2, i1 zeroext %3) #0 {
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32*, align 8
  %8 = alloca i8, align 1
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store i32* %2, i32** %7, align 8
  %9 = zext i1 %3 to i8
  store i8 %9, i8* %8, align 1
  %10 = load i32, i32* %5, align 4
  %11 = load i32, i32* %6, align 4
  %12 = add nsw i32 %10, %11
  ret i32 %12
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32* @_Z3fooP1b(%struct.b* %0) #0 {
  %2 = alloca %struct.b*, align 8
  store %struct.b* %0, %struct.b** %2, align 8
  %3 = load %struct.b*, %struct.b** %2, align 8
  %4 = getelementptr inbounds %struct.b, %struct.b* %3, i32 0, i32 2
  %5 = getelementptr inbounds %struct.a, %struct.a* %4, i32 0, i32 0
  ret i32* %5
}

; Function Attrs: noinline norecurse nounwind optnone uwtable
define dso_local i32 @main() #1 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca %struct.a, align 4
  %5 = alloca i32, align 4
  %6 = alloca [100 x i32], align 16
  %7 = alloca i32, align 4
  %8 = alloca i32, align 4
  %9 = alloca i32, align 4
  %10 = alloca i32, align 4
  %11 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 1, i32* %2, align 4
  %12 = load i32, i32* %2, align 4
  %13 = ashr i32 %12, 23
  store i32 %13, i32* %3, align 4
  %14 = load i32, i32* %2, align 4
  %15 = icmp sgt i32 %14, 1
  br i1 %15, label %16, label %19

16:                                               ; preds = %0
  %17 = load i32, i32* %2, align 4
  %18 = add nsw i32 %17, 1
  store i32 %18, i32* %3, align 4
  br label %27

19:                                               ; preds = %0
  %20 = load i32, i32* %2, align 4
  %21 = icmp sgt i32 %20, 0
  br i1 %21, label %22, label %25

22:                                               ; preds = %19
  %23 = load i32, i32* %2, align 4
  %24 = sub nsw i32 %23, 1
  store i32 %24, i32* %3, align 4
  br label %26

25:                                               ; preds = %19
  store i32 0, i32* %3, align 4
  br label %26

26:                                               ; preds = %25, %22
  br label %27

27:                                               ; preds = %26, %16
  %28 = load i32, i32* %3, align 4
  %29 = add nsw i32 %28, 1
  store i32 %29, i32* %3, align 4
  store i32 0, i32* %5, align 4
  store i32 1, i32* %7, align 4
  store i32 2, i32* %8, align 4
  %30 = load i32, i32* %7, align 4
  %31 = load i32, i32* %8, align 4
  %32 = and i32 %30, %31
  store i32 %32, i32* %9, align 4
  store i32 1, i32* %10, align 4
  br label %33

33:                                               ; preds = %50, %27
  %34 = load i32, i32* %10, align 4
  %35 = icmp slt i32 %34, 2
  br i1 %35, label %36, label %53

36:                                               ; preds = %33
  store i32 1, i32* %11, align 4
  br label %37

37:                                               ; preds = %44, %36
  %38 = load i32, i32* %11, align 4
  %39 = icmp slt i32 %38, 3
  br i1 %39, label %40, label %47

40:                                               ; preds = %37
  %41 = load i32, i32* %11, align 4
  %42 = load i32, i32* %5, align 4
  %43 = add nsw i32 %42, %41
  store i32 %43, i32* %5, align 4
  br label %44

44:                                               ; preds = %40
  %45 = load i32, i32* %11, align 4
  %46 = add nsw i32 %45, 1
  store i32 %46, i32* %11, align 4
  br label %37

47:                                               ; preds = %37
  %48 = load i32, i32* %5, align 4
  %49 = add nsw i32 %48, 2
  store i32 %49, i32* %5, align 4
  br label %50

50:                                               ; preds = %47
  %51 = load i32, i32* %10, align 4
  %52 = add nsw i32 %51, 1
  store i32 %52, i32* %10, align 4
  br label %33

53:                                               ; preds = %33
  ret i32 0
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { noinline norecurse nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
