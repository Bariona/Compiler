; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: nofree nounwind uwtable
define dso_local noalias i8* @_toy_malloc(i32 %mallocSize) local_unnamed_addr #0 {
entry:
  %conv = sext i32 %mallocSize to i64
  %call = tail call noalias i8* @malloc(i64 %conv) #9
  ret i8* %call
}

; Function Attrs: nofree nounwind
declare dso_local noalias i8* @malloc(i64) local_unnamed_addr #1

; Function Attrs: nofree nounwind uwtable
define dso_local void @print(i8* %str) local_unnamed_addr #0 {
entry:
  %call = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %str)
  ret void
}

; Function Attrs: nofree nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nofree nounwind uwtable
define dso_local void @println(i8* nocapture readonly %str) local_unnamed_addr #0 {
entry:
  %puts = tail call i32 @puts(i8* nonnull dereferenceable(1) %str)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local void @printInt(i32 %n) local_unnamed_addr #0 {
entry:
  %call = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %n)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local void @printlnInt(i32 %n) local_unnamed_addr #0 {
entry:
  %call = tail call i32 (i8*, ...) @printf(i8* nonnull dereferenceable(1) getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %n)
  ret void
}

; Function Attrs: nofree nounwind uwtable
define dso_local i8* @getString() local_unnamed_addr #0 {
entry:
  %call = tail call noalias dereferenceable_or_null(1024) i8* @malloc(i64 1024) #9
  %call1 = tail call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %call)
  ret i8* %call
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.start.p0i8(i64 immarg, i8* nocapture) #2

; Function Attrs: nofree nounwind
declare dso_local i32 @__isoc99_scanf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.lifetime.end.p0i8(i64 immarg, i8* nocapture) #2

; Function Attrs: nounwind uwtable
define dso_local i32 @getInt() local_unnamed_addr #3 {
entry:
  %x = alloca i32, align 4
  %0 = bitcast i32* %x to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %0) #9
  %call = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* nonnull %x)
  %1 = load i32, i32* %x, align 4, !tbaa !2
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %0) #9
  ret i32 %1
}

; Function Attrs: nofree nounwind uwtable
define dso_local noalias i8* @toString(i32 %i) local_unnamed_addr #0 {
entry:
  %call = tail call noalias dereferenceable_or_null(13) i8* @malloc(i64 13) #9
  %call1 = tail call i32 (i8*, i8*, ...) @sprintf(i8* nonnull dereferenceable(1) %call, i8* nonnull dereferenceable(1) getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %i) #9
  ret i8* %call
}

; Function Attrs: nofree nounwind
declare dso_local i32 @sprintf(i8* noalias nocapture, i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nofree nounwind uwtable
define dso_local i8* @_str_add(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #0 {
entry:
  %call = tail call i64 @strlen(i8* nonnull dereferenceable(1) %s1) #10
  %call1 = tail call i64 @strlen(i8* nonnull dereferenceable(1) %s2) #10
  %add = add i64 %call, 1
  %add2 = add i64 %add, %call1
  %call3 = tail call noalias i8* @malloc(i64 %add2) #9
  %call4 = tail call i8* @strcpy(i8* nonnull dereferenceable(1) %call3, i8* nonnull dereferenceable(1) %s1) #9
  %call5 = tail call i8* @strcat(i8* nonnull dereferenceable(1) %call3, i8* nonnull dereferenceable(1) %s2) #9
  ret i8* %call3
}

; Function Attrs: argmemonly nofree nounwind readonly
declare dso_local i64 @strlen(i8* nocapture) local_unnamed_addr #4

; Function Attrs: nofree nounwind
declare dso_local i8* @strcpy(i8* noalias returned, i8* noalias nocapture readonly) local_unnamed_addr #1

; Function Attrs: nofree nounwind
declare dso_local i8* @strcat(i8* returned, i8* nocapture readonly) local_unnamed_addr #1

; Function Attrs: nounwind readonly uwtable
define dso_local zeroext i1 @_str_eq(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #5 {
entry:
  %call = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %s1, i8* nonnull dereferenceable(1) %s2) #10
  %tobool = icmp eq i32 %call, 0
  ret i1 %tobool
}

; Function Attrs: nofree nounwind readonly
declare dso_local i32 @strcmp(i8* nocapture, i8* nocapture) local_unnamed_addr #6

; Function Attrs: nounwind readonly uwtable
define dso_local zeroext i1 @_str_neq(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #5 {
entry:
  %call = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %s1, i8* nonnull dereferenceable(1) %s2) #10
  %cmp = icmp ne i32 %call, 0
  ret i1 %cmp
}

; Function Attrs: nounwind readonly uwtable
define dso_local zeroext i1 @_str_slt(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #5 {
entry:
  %call = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %s1, i8* nonnull dereferenceable(1) %s2) #10
  %cmp = icmp slt i32 %call, 0
  ret i1 %cmp
}

; Function Attrs: nounwind readonly uwtable
define dso_local zeroext i1 @_str_sle(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #5 {
entry:
  %call = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %s1, i8* nonnull dereferenceable(1) %s2) #10
  %cmp = icmp slt i32 %call, 1
  ret i1 %cmp
}

; Function Attrs: nounwind readonly uwtable
define dso_local zeroext i1 @_str_sgt(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #5 {
entry:
  %call = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %s1, i8* nonnull dereferenceable(1) %s2) #10
  %cmp = icmp sgt i32 %call, 0
  ret i1 %cmp
}

; Function Attrs: nounwind readonly uwtable
define dso_local zeroext i1 @_str_sge(i8* nocapture readonly %s1, i8* nocapture readonly %s2) local_unnamed_addr #5 {
entry:
  %call = tail call i32 @strcmp(i8* nonnull dereferenceable(1) %s1, i8* nonnull dereferenceable(1) %s2) #10
  %cmp = icmp sgt i32 %call, -1
  ret i1 %cmp
}

; Function Attrs: nounwind readonly uwtable
define dso_local i32 @_str_length(i8* nocapture readonly %str) local_unnamed_addr #5 {
entry:
  %call = tail call i64 @strlen(i8* nonnull dereferenceable(1) %str) #10
  %conv = trunc i64 %call to i32
  ret i32 %conv
}

; Function Attrs: nounwind uwtable
define dso_local noalias i8* @_str_substring(i8* nocapture readonly %str, i32 %left, i32 %right) local_unnamed_addr #3 {
entry:
  %sub = sub nsw i32 %right, %left
  %add = add nsw i32 %sub, 1
  %conv = sext i32 %add to i64
  %call = tail call noalias i8* @malloc(i64 %conv) #9
  %idx.ext = sext i32 %left to i64
  %add.ptr = getelementptr inbounds i8, i8* %str, i64 %idx.ext
  %conv2 = sext i32 %sub to i64
  tail call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %call, i8* align 1 %add.ptr, i64 %conv2, i1 false)
  ret i8* %call
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #2

; Function Attrs: nounwind uwtable
define dso_local i32 @_str_parseInt(i8* nocapture readonly %str) local_unnamed_addr #3 {
entry:
  %ret = alloca i32, align 4
  %0 = bitcast i32* %ret to i8*
  call void @llvm.lifetime.start.p0i8(i64 4, i8* nonnull %0) #9
  %call = call i32 (i8*, i8*, ...) @__isoc99_sscanf(i8* %str, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* nonnull %ret) #9
  %1 = load i32, i32* %ret, align 4, !tbaa !2
  call void @llvm.lifetime.end.p0i8(i64 4, i8* nonnull %0) #9
  ret i32 %1
}

; Function Attrs: nofree nounwind
declare dso_local i32 @__isoc99_sscanf(i8* nocapture readonly, i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local i32 @_str_ord(i8* nocapture readonly %str, i32 %pos) local_unnamed_addr #7 {
entry:
  %idxprom = sext i32 %pos to i64
  %arrayidx = getelementptr inbounds i8, i8* %str, i64 %idxprom
  %0 = load i8, i8* %arrayidx, align 1, !tbaa !6
  %conv = sext i8 %0 to i32
  ret i32 %conv
}

; Function Attrs: nofree nounwind
declare i32 @puts(i8* nocapture readonly) local_unnamed_addr #8

attributes #0 = { nofree nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #1 = { nofree nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="true" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #2 = { argmemonly nounwind willreturn }
attributes #3 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #4 = { argmemonly nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="true" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #5 = { nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #6 = { nofree nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "no-infs-fp-math"="true" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #7 = { norecurse nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="none" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="true" "no-jump-tables"="false" "no-nans-fp-math"="true" "no-signed-zeros-fp-math"="true" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="true" "use-soft-float"="false" }
attributes #8 = { nofree nounwind }
attributes #9 = { nounwind }
attributes #10 = { nounwind readonly }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
!2 = !{!3, !3, i64 0}
!3 = !{!"int", !4, i64 0}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
!6 = !{!4, !4, i64 0}
