TITLE OPROppCodeOne

.code
CLAOppCode PROC uses eax
	mov eax, 0
	mov Accumulator , eax
	ret
CLAOppCode ENDP

CLLOppCode PROC uses eax
	mov eax , 0
	mov Link , eax
	ret
CLLOppCode ENDP

CMAOppCode PROC uses eax
	mov eax , Accumulator
	or Accumulator , eax
	ret
CMAOppCode ENDP

CMLOppCode PROC uses eax
	mov eax , Link
	or Link , eax
	ret
CMLOppCode ENDP

IACOppCode PROC uses eax
	mov eax , Accumulator
	inc eax
	mov Accumulator , eax
	ret
IACOppCode ENDP

RAROppCode PROC uses eax edx
	mov eax , Accumulator
	and eax , 100000000000b
	shr eax , 11
	mov edx , Link
	cmp eax , 1d
	je NONZERO
	jmp ZERO
ZERO:
	mov eax , Accumulator
	shr eax , 1
	and eax , 011111111111b
	mov Accumulator , eax
	or edx , 1b 
	ret
NONZERO:
	mov eax , Accumulator
	shr eax , 1
	or eax , 100000000000b
	mov Accumulator , eax
	and edx , 0b 
	ret
RAROppCode ENDP

RTROppCode PROC uses eax
	call RAROppCode
	call RAROppCode
	ret
RTROppCode ENDP

RALOppCode PROC uses eax edx
	mov eax , Accumulator
	and eax , 100000000000b
	shr eax , 11
	mov edx , Link
	cmp eax , 0d
	jnz NONZERO
	jmp ZERO
ZERO:
	mov eax , Accumulator
	shl eax , 1
	and eax , 0111111111111b
	mov Accumulator , eax
	or edx , 1b 
	ret
NONZERO:
	mov eax , Accumulator
	shl eax , 1
	or eax , 1b
	and eax , 0111111111111b
	mov Accumulator , eax
	or edx , 1b 
	ret
RALOppCode ENDP

RTLOppCode PROC uses eax
	call RALOppCode
	call RALOppCode
	ret
RTLOppCode ENDP

BSWOppCode PROC uses edx eax esi
	mov edx , OFFSET ExecuteOppCodeMessageOne
	call WriteString
	ret
BSWOppCode ENDP