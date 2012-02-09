TITLE OPROppCodeOne

.code

OPROppCodeSelection PROC uses eax
	mov eax , CurrentInstruction
	and eax , 000100000000b
	shr eax , 8
	cmp eax , 0d
	je ZERO
	jmp ONE
ZERO:
	mov eax , 0d
	mov OPROppCodeCounter , eax
	call OPROppCodeOne
	ret
ONE:
	mov eax , 0d
	mov OPROppCodeCounter , eax
	call OPROppCodeTwo
	ret
OPROppCodeSelection ENDP

OPROppCodeOne PROC uses eax ebx esi edx
	mov ebx , OPROppCodeCounter
	mov eax , CurrentInstruction
	and eax , 000011111111b
	jmp CONTINUE
ONE:
	mov esi , ebx
	imul esi , 4
	mov edx , [esi + OPROppCodeArrayOne]
	inc ebx
	call edx
CONTINUE:
	cmp eax , 0d
	je FINISHED
	shr eax , 1
	jc ONE
	inc ebx
	jmp CONTINUE
FINISHED:
	ret
OPROppCodeOne ENDP

OPROppCodeTwo PROC uses eax 
	mov eax , CurrentInstruction
	and eax , 000000001000b
	shr eax , 3
	cmp eax , 0d
	je ZERO
	jmp ONE
ZERO:
	mov eax , 0d
	mov OPROppCodeCounter , eax
	call OPROppCodeTwoOr
	ret
ONE:
	mov eax , 0d
	mov OPROppCodeCounter , eax
	call OPROppCodeTwoAnd
	ret
OPROppCodeTwo ENDP

OPROppCodeTwoOr PROC uses eax ebx esi edx
	mov ebx , OPROppCodeCounter
	mov eax , CurrentInstruction
	and eax , 000011111111b
	jmp CONTINUE
ONE:
	mov esi , ebx
	imul esi , 4
	mov edx , [esi + OPROppCodeArrayTwoOr]
	inc ebx
	call edx
CONTINUE:
	cmp eax , 0d
	je FINISHED
	shr eax , 1
	jc ONE
	inc ebx
	jmp CONTINUE
FINISHED:
	ret
OPROppCodeTwoOr ENDP

OPROppCodeTwoAnd PROC uses eax ebx esi edx
	mov ebx , OPROppCodeCounter
	mov eax , CurrentInstruction
	and eax , 000011111111b
	jmp CONTINUE
ONE:
	mov esi , ebx
	imul esi , 4
	mov edx , [esi + OPROppCodeArrayTwoAnd]
	inc ebx
	call edx
CONTINUE:
	cmp eax , 0d
	je FINISHED
	shr eax , 1
	jc ONE
	inc ebx
	jmp CONTINUE
FINISHED:
	ret
OPROppCodeTwoAnd ENDP
