package Backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class builtInPrinter {

//    String builtIn="debug";
String builtIn="""
        	.text
        	.align	2
        	.globl	__builtIn__malloc
        __builtIn__malloc:
        	tail	malloc
        	.align	2
        	.globl	__builtIn__stringAdd
        __builtIn__stringAdd:
        	addi	sp,sp,-32
        	sw	ra,28(sp)
        	sw	s0,24(sp)
        	sw	s1,20(sp)
        	sw	s2,16(sp)
        	sw	s3,12(sp)
        	sw	s4,8(sp)
        	mv	s2,a1
        	mv	s4,a0
        	call	strlen
        	mv	s0,a0
        	mv	a0,s2
        	call	strlen
        	mv	s3,a0
        	add	a0,s0,a0
        	addi	a0,a0,1
        	call	malloc
        	mv	a2,s0
        	mv	a1,s4
        	mv	s1,a0
        	call	memcpy
        	add	a0,s1,s0
        	addi	a2,s3,1
        	mv	a1,s2
        	call	memcpy
        	lw	ra,28(sp)
        	lw	s0,24(sp)
        	lw	s2,16(sp)
        	lw	s3,12(sp)
        	lw	s4,8(sp)
        	mv	a0,s1
        	lw	s1,20(sp)
        	addi	sp,sp,32
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringEq
        __builtIn__stringEq:
        	addi	sp,sp,-16
        	sw	ra,12(sp)
        	call	strcmp
        	lw	ra,12(sp)
        	seqz	a0,a0
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringNe
        __builtIn__stringNe:
        	addi	sp,sp,-16
        	sw	ra,12(sp)
        	call	strcmp
        	lw	ra,12(sp)
        	snez	a0,a0
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringLt
        __builtIn__stringLt:
        	addi	sp,sp,-16
        	sw	ra,12(sp)
        	call	strcmp
        	lw	ra,12(sp)
        	srli	a0,a0,31
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringLe
        __builtIn__stringLe:
        	addi	sp,sp,-16
        	sw	ra,12(sp)
        	call	strcmp
        	lw	ra,12(sp)
        	slti	a0,a0,1
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringGt
        __builtIn__stringGt:
        	addi	sp,sp,-16
        	sw	ra,12(sp)
        	call	strcmp
        	lw	ra,12(sp)
        	sgt	a0,a0,zero
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringGe
        __builtIn__stringGe:
        	addi	sp,sp,-16
        	sw	ra,12(sp)
        	call	strcmp
        	lw	ra,12(sp)
        	not	a0,a0
        	srli	a0,a0,31
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringLength
        __builtIn__stringLength:
        	tail	strlen
        	.align	2
        	.globl	__builtIn__substring
        __builtIn__substring:
        	addi	sp,sp,-32
        	sw	s0,24(sp)
        	sub	s0,a2,a1
        	sw	s3,12(sp)
        	mv	s3,a0
        	addi	a0,s0,1
        	sw	ra,28(sp)
        	sw	s1,20(sp)
        	sw	s2,16(sp)
        	mv	s2,a1
        	call	malloc
        	mv	s1,a0
        	add	a1,s3,s2
        	mv	a2,s0
        	add	s0,s1,s0
        	call	memcpy
        	sb	zero,0(s0)
        	lw	ra,28(sp)
        	lw	s0,24(sp)
        	lw	s2,16(sp)
        	lw	s3,12(sp)
        	mv	a0,s1
        	lw	s1,20(sp)
        	addi	sp,sp,32
        	jr	ra
        	.section	.rodata.str1.4,"aMS",@progbits,1
        	.align	2
        .LC0:
        	.string	"%d"
        	.text
        	.align	2
        	.globl	__builtIn__parseInt
        __builtIn__parseInt:
        	addi	sp,sp,-32
        	lui	a1,%hi(.LC0)
        	addi	a2,sp,12
        	addi	a1,a1,%lo(.LC0)
        	sw	ra,28(sp)
        	call	sscanf
        	lw	ra,28(sp)
        	lw	a0,12(sp)
        	addi	sp,sp,32
        	jr	ra
        	.align	2
        	.globl	__builtIn__stringOrd
        __builtIn__stringOrd:
        	add	a0,a0,a1
        	lbu	a0,0(a0)
        	ret
        	.section	.rodata.str1.4
        	.align	2
        .LC1:
        	.string	"%s"
        	.text
        	.align	2
        	.globl	__builtIn__print
        __builtIn__print:
        	mv	a1,a0
        	lui	a0,%hi(.LC1)
        	addi	a0,a0,%lo(.LC1)
        	tail	printf
        	.align	2
        	.globl	__builtIn__println
        __builtIn__println:
        	tail	puts
        	.align	2
        	.globl	__builtIn__printInt
        __builtIn__printInt:
        	mv	a1,a0
        	lui	a0,%hi(.LC0)
        	addi	a0,a0,%lo(.LC0)
        	tail	printf
        	.section	.rodata.str1.4
        	.align	2
        .LC2:
        	.string	"%d\\n"
        	.text
        	.align	2
        	.globl	__builtIn__printlnInt
        __builtIn__printlnInt:
        	mv	a1,a0
        	lui	a0,%hi(.LC2)
        	addi	a0,a0,%lo(.LC2)
        	tail	printf
        	.align	2
        	.globl	__builtIn__getString
        __builtIn__getString:
        	addi	sp,sp,-16
        	li	a0,256
        	sw	ra,12(sp)
        	sw	s0,8(sp)
        	call	malloc
        	mv	s0,a0
        	mv	a1,a0
        	lui	a0,%hi(.LC1)
        	addi	a0,a0,%lo(.LC1)
        	call	scanf
        	lw	ra,12(sp)
        	mv	a0,s0
        	lw	s0,8(sp)
        	addi	sp,sp,16
        	jr	ra
        	.align	2
        	.globl	__builtIn__getInt
        __builtIn__getInt:
        	addi	sp,sp,-32
        	lui	a0,%hi(.LC0)
        	addi	a1,sp,12
        	addi	a0,a0,%lo(.LC0)
        	sw	ra,28(sp)
        	call	scanf
        	lw	ra,28(sp)
        	lw	a0,12(sp)
        	addi	sp,sp,32
        	jr	ra
        	.align	2
        	.globl	__builtIn__toString
        __builtIn__toString:
        	addi	sp,sp,-16
        	sw	s1,4(sp)
        	mv	s1,a0
        	li	a0,16
        	sw	ra,12(sp)
        	sw	s0,8(sp)
        	call	malloc
        	lui	a1,%hi(.LC0)
        	mv	a2,s1
        	addi	a1,a1,%lo(.LC0)
        	mv	s0,a0
        	call	sprintf
        	lw	ra,12(sp)
        	mv	a0,s0
        	lw	s0,8(sp)
        	lw	s1,4(sp)
        	addi	sp,sp,16
        	jr	ra
        	.ident	"GCC: (GNU) 10.1.0"
        """;

public builtInPrinter(String fileName)throws IOException{
    FileOutputStream fos= new FileOutputStream(fileName);
    fos.write(builtIn.getBytes());
    fos.close();
}
}
